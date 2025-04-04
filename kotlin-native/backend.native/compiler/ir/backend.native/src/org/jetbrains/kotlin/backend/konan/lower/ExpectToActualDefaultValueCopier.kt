/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.lower

import org.jetbrains.kotlin.backend.konan.descriptors.isExpectMember
import org.jetbrains.kotlin.backend.konan.descriptors.propertyIfAccessor
import org.jetbrains.kotlin.backend.konan.ir.ModuleIndex
import org.jetbrains.kotlin.descriptors.MemberDescriptor
import org.jetbrains.kotlin.ir.IrBuiltIns
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.symbols.*
import org.jetbrains.kotlin.ir.util.DeepCopyIrTreeWithSymbols
import org.jetbrains.kotlin.ir.util.DeepCopySymbolRemapper
import org.jetbrains.kotlin.ir.util.patchDeclarationParents
import org.jetbrains.kotlin.ir.util.unexpectedSymbolKind
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.ir.visitors.acceptVoid
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.resolve.multiplatform.OptionalAnnotationUtil
import org.jetbrains.kotlin.resolve.multiplatform.findCompatibleActualsForExpected

@OptIn(ObsoleteDescriptorBasedAPI::class)
internal class ExpectToActualDefaultValueCopier(private val irModule: IrModuleFragment, private val irBuiltIns: IrBuiltIns) {

    // Note: local declarations aren't required here; TODO: use more lightweight index.
    private val moduleIndex = ModuleIndex(irModule)

    fun process() {
        irModule.files.forEach { this.process(it) }
    }

    private fun process(irFile: IrFile) {
        // All declarations with `isExpect == true` are nested into a top-level declaration with `isExpect == true`.
        irFile.declarations.forEach {
            if (it.descriptor.isExpectMember) {
                copyDefaultArgumentsFromExpectToActual(it)
            }
        }
    }

    private fun copyDefaultArgumentsFromExpectToActual(declaration: IrDeclaration) {
        declaration.acceptVoid(object : IrVisitorVoid() {
            override fun visitElement(element: IrElement) {
                element.acceptChildrenVoid(this)
            }

            override fun visitValueParameter(declaration: IrValueParameter) {
                super.visitValueParameter(declaration)

                val defaultValue = declaration.defaultValue ?: return
                val function = declaration.parent as IrFunction

                val index = declaration.indexInParameters
                assert(function.parameters[index] == declaration)

                if (function is IrConstructor && OptionalAnnotationUtil.isOptionalAnnotationClass(function.descriptor.constructedClass)) {
                    return
                }

                val actualForExpected = function.findActualForExpected()
                actualForExpected.parameters[index].defaultValue =
                        irBuiltIns.irFactory.createExpressionBody(
                                defaultValue.startOffset, defaultValue.endOffset,
                                defaultValue.expression.remapExpectValueSymbols().patchDeclarationParents(actualForExpected)
                        )
            }
        })
    }

    private inline fun <reified T : IrFunction> T.findActualForExpected(): T =
            moduleIndex.functions[descriptor.findActualForExpect()] as T

    private fun IrProperty.findActualForExpected(): IrProperty =
            moduleIndex.properties[descriptor.findActualForExpect()]!!

    private fun IrClass.findActualForExpected(): IrClass =
            moduleIndex.classes[descriptor.findActualForExpect()]!!

    private fun IrEnumEntry.findActualForExpected(): IrEnumEntry =
            moduleIndex.enumEntries[descriptor.findActualForExpect()]!!

    private inline fun <reified T : MemberDescriptor> T.findActualForExpect(): T {
        if (!this.isExpect) error(this)
        return (findCompatibleActualsForExpected(module).singleOrNull() ?: error(this)) as T
    }

    private fun IrExpression.remapExpectValueSymbols(): IrExpression {
        class SymbolRemapper : DeepCopySymbolRemapper() {
            override fun getReferencedClass(symbol: IrClassSymbol) =
                    if (symbol.descriptor.isExpect)
                        symbol.owner.findActualForExpected().symbol
                    else super.getReferencedClass(symbol)

            override fun getReferencedTypeParameter(symbol: IrTypeParameterSymbol): IrTypeParameterSymbol {
                return remapExpectTypeParameter(symbol).symbol
            }

            override fun getReferencedScript(symbol: IrScriptSymbol): IrScriptSymbol {
                symbol.unexpectedSymbolKind<IrScriptSymbol>()
            }

            override fun getReferencedConstructor(symbol: IrConstructorSymbol) =
                    if (symbol.descriptor.isExpect)
                        symbol.owner.findActualForExpected().symbol
                    else super.getReferencedConstructor(symbol)

            override fun getReferencedSimpleFunction(symbol: IrSimpleFunctionSymbol) = when {
                symbol.descriptor.isExpect -> symbol.owner.findActualForExpected().symbol

                symbol.descriptor.propertyIfAccessor.isExpect -> {
                    val property = symbol.owner.correspondingPropertySymbol!!.owner
                    val actualPropertyDescriptor = property.descriptor.findActualForExpect()
                    val accessorDescriptor = when (symbol.owner) {
                        property.getter -> actualPropertyDescriptor.getter!!
                        property.setter -> actualPropertyDescriptor.setter!!
                        else -> error("Unexpected accessor of $symbol ${symbol.descriptor}")
                    }
                    moduleIndex.functions[accessorDescriptor]!!.symbol as IrSimpleFunctionSymbol
                }

                else -> super.getReferencedSimpleFunction(symbol)
            }

            override fun getReferencedProperty(symbol: IrPropertySymbol) =
                    if (symbol.descriptor.isExpect)
                        symbol.owner.findActualForExpected().symbol
                    else
                        super.getReferencedProperty(symbol)

            override fun getReferencedEnumEntry(symbol: IrEnumEntrySymbol): IrEnumEntrySymbol =
                    if (symbol.descriptor.isExpect)
                        symbol.owner.findActualForExpected().symbol
                    else
                        super.getReferencedEnumEntry(symbol)

            override fun getReferencedValue(symbol: IrValueSymbol) =
                    remapExpectValue(symbol)?.symbol ?: super.getReferencedValue(symbol)
        }

        val symbolRemapper = SymbolRemapper()
        acceptVoid(symbolRemapper)
        return transform(DeepCopyIrTreeWithSymbols(symbolRemapper), data = null)
    }

    private fun remapExpectTypeParameter(symbol: IrTypeParameterSymbol): IrTypeParameter {
        val parameter = symbol.owner
        val parent = parameter.parent

        return when (parent) {
            is IrClass ->
                if (!parent.descriptor.isExpect)
                    parameter
                else parent.findActualForExpected().typeParameters[parameter.index]

            is IrFunction ->
                if (!parent.descriptor.isExpect)
                    parameter
                else parent.findActualForExpected().typeParameters[parameter.index]

            else -> error(parent)
        }
    }

    private fun remapExpectValue(symbol: IrValueSymbol): IrValueParameter? {
        if (symbol !is IrValueParameterSymbol) {
            return null
        }

        val parameter = symbol.owner
        val parent = parameter.parent

        return when (parent) {
            is IrClass ->
                if (!parent.descriptor.isExpect)
                    null
                else {
                    assert(parameter == parent.thisReceiver)
                    parent.findActualForExpected().thisReceiver!!
                }

            is IrFunction ->
                if (!parent.descriptor.isExpect)
                    null
                else {
                    assert(parent.parameters[parameter.indexInParameters] == parameter)
                    parent.findActualForExpected().parameters[parameter.indexInParameters]
                }

            else -> error(parent)
        }
    }
}
