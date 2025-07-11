/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.plugin.sandbox.fir

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionApiInternals
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jetbrains.kotlin.plugin.sandbox.fir.generators.*
import org.jetbrains.kotlin.plugin.sandbox.fir.supertypeswithoverrides.MissingOverrideStatusTransformer
import org.jetbrains.kotlin.plugin.sandbox.fir.supertypeswithoverrides.SimpleAddSupertypeExtension
import org.jetbrains.kotlin.plugin.sandbox.fir.types.FirNumberSignAttributeExtension
import org.jetbrains.kotlin.plugin.sandbox.fir.types.SandboxFunctionTypeKindExtension
import org.jetbrains.kotlin.plugin.sandbox.ir.GeneratedDeclarationsIrBodyFiller

class FirPluginPrototypeExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::AllOpenStatusTransformer
        +::AllOpenMatcherBasedStatusTransformer
        +::AllOpenPredicateMatcher
        +::AllPublicVisibilityTransformer
        +::SomeAdditionalSupertypeGenerator
        +::AdditionalSuperclassGenerator
        +::SupertypeWithArgumentGenerator
        +::PluginAdditionalCheckers
        +::FirNumberSignAttributeExtension
        +::AlgebraReceiverInjector
        +::SandboxFunctionTypeKindExtension
        @OptIn(FirExtensionApiInternals::class)
        +::DataFrameLikeCallsRefinementExtension
        +::DataFrameLikeReturnTypeInjector
        +::CallDataStorage

        // Declaration generators
        +::TopLevelDeclarationsGenerator
        +::TopLevelPrivateSuspendFunctionGenerator
        +::ExternalClassGenerator
        +::AdditionalMembersGenerator
        +::CompanionGenerator
        +::MembersOfSerializerGenerator
        +::DataFrameLikeTypeMembersGenerator

        +::NestedClassGeneratorWithSupertypesDependantOnAnnotationArgument
        +::NestedClassGeneratorWithLocalClassesSupport
        +::NestedClassSupertypesDependantOnAnnotationArgumentAdder
        +::SupertypesDependantOnAnnotationArgumentComponent

        // Test generators for https://github.com/JetBrains/kotlin/pull/5466
        +::MissingOverrideStatusTransformer
        +::SimpleAddSupertypeExtension
    }
}

class FirPluginPrototypeComponentRegistrar : CompilerPluginRegistrar() {
    override val supportsK2: Boolean
        get() = true

    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        FirExtensionRegistrarAdapter.registerExtension(FirPluginPrototypeExtensionRegistrar())
        IrGenerationExtension.registerExtension(GeneratedDeclarationsIrBodyFiller())
    }
}
