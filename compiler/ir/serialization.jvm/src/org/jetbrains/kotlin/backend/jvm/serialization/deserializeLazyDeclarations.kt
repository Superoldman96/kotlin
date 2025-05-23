/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.jvm.serialization

import org.jetbrains.kotlin.backend.common.linkage.partial.PartialLinkageSupportForLinker
import org.jetbrains.kotlin.backend.common.overrides.FakeOverrideDeclarationTable
import org.jetbrains.kotlin.backend.common.overrides.FileLocalAwareLinker
import org.jetbrains.kotlin.backend.common.overrides.IrLinkerFakeOverrideProvider
import org.jetbrains.kotlin.backend.common.serialization.*
import org.jetbrains.kotlin.backend.common.serialization.encodings.BinarySymbolData
import org.jetbrains.kotlin.backend.jvm.serialization.proto.JvmIr
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.ir.IrBuiltIns
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.IrProvider
import org.jetbrains.kotlin.ir.backend.jvm.serialization.JvmIrMangler
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.declarations.impl.IrFileImpl
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSymbol
import org.jetbrains.kotlin.ir.symbols.impl.IrFileSymbolImpl
import org.jetbrains.kotlin.ir.types.IrTypeSystemContext
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.ir.visitors.acceptVoid
import org.jetbrains.kotlin.backend.common.serialization.proto.FileEntry as ProtoFileEntry
import org.jetbrains.kotlin.backend.common.serialization.proto.IdSignature as ProtoIdSignature
import org.jetbrains.kotlin.backend.common.serialization.proto.IrDeclaration as ProtoDeclaration
import org.jetbrains.kotlin.backend.common.serialization.proto.IrExpression as ProtoExpression
import org.jetbrains.kotlin.backend.common.serialization.proto.IrStatement as ProtoStatement
import org.jetbrains.kotlin.backend.common.serialization.proto.IrType as ProtoType

fun deserializeFromByteArray(
    byteArray: ByteArray,
    irBuiltIns: IrBuiltIns,
    symbolTable: SymbolTable,
    irProviders: List<IrProvider>,
    toplevelParent: IrClass,
    typeSystemContext: IrTypeSystemContext,
) {
    val irInterner = IrInterningService()
    val irProto = JvmIr.ClassOrFile.parseFrom(byteArray.codedInputStream)
    val irLibraryFile = IrLibraryFileFromAnnotation(
        irProto.typeList,
        irProto.signatureList,
        irProto.stringList,
        irProto.bodyList,
        irProto.debugInfoList,
        irProto.fileEntryList,
    )

    // Only needed for local signature computation.
    val dummyIrFile = IrFileImpl(NaiveSourceBasedFileEntryImpl("<unknown>"), IrFileSymbolImpl(), toplevelParent.packageFqName!!)
    // On JVM, file-scope private declarations are uniquely identified by file facade's fq name.
    val dummyFileSignature = IdSignature.FileSignature(irProto.fileFacadeFqName, toplevelParent.packageFqName!!, "<unknown>")

    val symbolDeserializer = IrSymbolDeserializer(
        symbolTable,
        irLibraryFile,
        fileSymbol = dummyIrFile.symbol,
        fileSignature = dummyFileSignature,
        enqueueLocalTopLevelDeclaration = {}, // just link to it in symbolTable
        irInterner = irInterner
    ) { idSignature, symbolKind ->
        referencePublicSymbol(symbolTable, idSignature, symbolKind)
    }

    // We have to supply topLevelParent here, but this results in wrong values for parent fields in deeply embedded declarations.
    // Patching will be needed.
    val deserializer = IrDeclarationDeserializer(
        irBuiltIns, symbolTable, irBuiltIns.irFactory, irLibraryFile, toplevelParent,
        settings = IrDeserializationSettings(
            allowAlreadyBoundSymbols = true,
        ),
        symbolDeserializer,
        onDeserializedClass = { _, _ -> },
        needToDeserializeFakeOverrides = { false },
        specialProcessingForMismatchedSymbolKind = null,
        irInterner = irInterner
    )
    for (declarationProto in irProto.declarationList) {
        deserializer.deserializeDeclaration(declarationProto, setParent = false)
    }

    val signaturer = symbolTable.signaturer
    if (signaturer == null) {
        ExternalDependenciesGenerator(symbolTable, irProviders).generateUnboundSymbolsAsDependencies()
    } else {
        signaturer.withFileSignature(dummyFileSignature) {
            ExternalDependenciesGenerator(symbolTable, irProviders).generateUnboundSymbolsAsDependencies()
        }
    }

    toplevelParent.safelyInitializeAllLazyDescendants()
    toplevelParent.patchDeclarationParents()
    buildFakeOverridesForLocalClasses(symbolTable, typeSystemContext, symbolDeserializer, toplevelParent)
}

private fun IrElement.safelyInitializeAllLazyDescendants() {
    // Traversal may trigger initialization of some child declaration,
    // which may trigger initialization of some other IR element (e.g., IrProperty -> its getter/setter IrFunctions).,
    // which may trigger adding it to its parent element (e.g. JvmFileFacadeClass),
    // which may happen to be some element we are currently traversing,
    // which would throw ConcurrentModificationException.
    // The workaround is to traverse the subtree over snapshots first.

    acceptVoid(object : IrVisitorVoid() {
        override fun visitElement(element: IrElement) {
            val directChildrenSnapshot = mutableListOf<IrElement>()
            element.acceptChildrenVoid(object : IrVisitorVoid() {
                override fun visitElement(element: IrElement) {
                    directChildrenSnapshot += element
                }
            })

            for (child in directChildrenSnapshot) {
                child.acceptChildrenVoid(this)
            }
        }
    })
}


private class IrLibraryFileFromAnnotation(
    private val types: List<ProtoType>,
    private val signatures: List<ProtoIdSignature>,
    private val strings: List<String>,
    private val bodies: List<JvmIr.XStatementOrExpression>,
    private val debugInfo: List<String>,
    private val fileEntries: List<ProtoFileEntry>,
) : IrLibraryFile() {
    override fun declaration(index: Int): ProtoDeclaration {
        error("This method is never supposed to be called")
    }

    override fun inlineDeclaration(index: Int): ProtoDeclaration {
        error("This method is never supposed to be called")
    }

    override fun type(index: Int): ProtoType = types[index]
    override fun signature(index: Int): ProtoIdSignature = signatures[index]
    override fun string(index: Int): String = strings[index]
    override fun debugInfo(index: Int): String = debugInfo[index]
    override fun fileEntry(index: Int): ProtoFileEntry = fileEntries[index]

    override fun expressionBody(index: Int): ProtoExpression =
        bodies[index].also { require(it.hasExpression()) }.expression

    override fun statementBody(index: Int): ProtoStatement =
        bodies[index].also { require(it.hasStatement()) }.statement
}

private fun referencePublicSymbol(
    symbolTable: SymbolTable,
    idSig: IdSignature,
    symbolKind: BinarySymbolData.SymbolKind
): IrSymbol {
    with(symbolTable) {
        return when (symbolKind) {
            BinarySymbolData.SymbolKind.CLASS_SYMBOL -> referenceClass(idSig)
            BinarySymbolData.SymbolKind.CONSTRUCTOR_SYMBOL -> referenceConstructor(idSig)
            BinarySymbolData.SymbolKind.ENUM_ENTRY_SYMBOL -> referenceEnumEntry(idSig)
            BinarySymbolData.SymbolKind.STANDALONE_FIELD_SYMBOL, BinarySymbolData.SymbolKind.FIELD_SYMBOL -> referenceField(idSig)
            BinarySymbolData.SymbolKind.FUNCTION_SYMBOL -> referenceSimpleFunction(idSig)
            BinarySymbolData.SymbolKind.TYPEALIAS_SYMBOL -> referenceTypeAlias(idSig)
            BinarySymbolData.SymbolKind.PROPERTY_SYMBOL -> referenceProperty(idSig)
            BinarySymbolData.SymbolKind.TYPE_PARAMETER_SYMBOL -> referenceTypeParameter(idSig)
            else -> error("Unexpected classifier symbol kind: $symbolKind for signature $idSig")
        }
    }
}

// TODO: implement properly
fun makeSimpleFakeOverrideBuilder(
    symbolTable: SymbolTable,
    typeSystemContext: IrTypeSystemContext,
    symbolDeserializer: IrSymbolDeserializer
): IrLinkerFakeOverrideProvider {
    return IrLinkerFakeOverrideProvider(
        object : FileLocalAwareLinker {
            override fun tryReferencingPropertyByLocalSignature(parent: IrDeclaration, idSignature: IdSignature): IrPropertySymbol =
                symbolDeserializer.referencePropertyByLocalSignature(idSignature)

            override fun tryReferencingSimpleFunctionByLocalSignature(
                parent: IrDeclaration, idSignature: IdSignature
            ): IrSimpleFunctionSymbol =
                symbolDeserializer.referenceSimpleFunctionByLocalSignature(idSignature)
        },
        symbolTable,
        JvmIrMangler,
        typeSystemContext,
        fakeOverrideDeclarationTable = PrePopulatedDeclarationTable(symbolDeserializer.deserializedSymbolsWithOwnersInCurrentFile),
        friendModules = emptyMap(), // TODO: provide friend modules
        partialLinkageSupport = PartialLinkageSupportForLinker.DISABLED
    )
}

private fun buildFakeOverridesForLocalClasses(
    symbolTable: SymbolTable,
    typeSystemContext: IrTypeSystemContext,
    symbolDeserializer: IrSymbolDeserializer,
    toplevel: IrClass
) {
    val builder = makeSimpleFakeOverrideBuilder(symbolTable, typeSystemContext, symbolDeserializer)
    toplevel.acceptChildrenVoid(
        object : IrVisitorVoid() {
            override fun visitElement(element: IrElement) {
                element.acceptChildrenVoid(this)
            }

            override fun visitClass(declaration: IrClass) {
                if (declaration.visibility == DescriptorVisibilities.LOCAL) {
                    builder.provideFakeOverrides(declaration, CompatibilityMode.CURRENT)
                }
                super.visitClass(declaration)
            }
        })
}

class PrePopulatedDeclarationTable(
    sig2symbol: Map<IdSignature, IrSymbol>
) : FakeOverrideDeclarationTable(JvmIrMangler) {
    private val symbol2Sig = sig2symbol.entries.associate { (x, y) -> y to x }

    override fun tryComputeBackendSpecificSignature(declaration: IrDeclaration): IdSignature? {
        symbol2Sig[declaration.symbol]?.let { return it }
        return super.tryComputeBackendSpecificSignature(declaration)
    }
}
