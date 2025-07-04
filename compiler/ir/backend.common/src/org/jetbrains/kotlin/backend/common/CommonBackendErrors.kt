/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.common

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.backend.common.BackendDiagnosticRenderers.EVALUATION_ERROR_EXPLANATION
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
import org.jetbrains.kotlin.diagnostics.error1
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
import org.jetbrains.kotlin.diagnostics.rendering.Renderer
import org.jetbrains.kotlin.ir.IrDiagnosticRenderers
import org.jetbrains.kotlin.ir.declarations.IrFunction

object CommonBackendErrors : KtDiagnosticsContainer() {
    val EVALUATION_ERROR by error1<PsiElement, String>()

    val INLINE_CALL_CYCLE by error1<PsiElement, IrFunction>()

    override fun getRendererFactory(): BaseDiagnosticRendererFactory {
        return KtDefaultCommonBackendErrorMessages
    }
}

object KtDefaultCommonBackendErrorMessages : BaseDiagnosticRendererFactory() {
    override val MAP by KtDiagnosticFactoryToRendererMap("KT") { map ->
        map.put(
            CommonBackendErrors.EVALUATION_ERROR,
            "Cannot evaluate constant expression: {0}",
            EVALUATION_ERROR_EXPLANATION,
        )
        map.put(
            CommonBackendErrors.INLINE_CALL_CYCLE,
            "The ''{0}'' invocation is a part of inline cycle",
            IrDiagnosticRenderers.DECLARATION_NAME,
        )
    }
}

object BackendDiagnosticRenderers {
    val EVALUATION_ERROR_EXPLANATION = Renderer<String> {
        val result = Regex("Exception (\\S+)(: (.*))?").matchEntire(it)?.groupValues
        val exceptionName = result?.getOrNull(1)
        val message = result?.getOrNull(3)
        when {
            !message.isNullOrBlank() -> message
            exceptionName == StackOverflowError::class.java.name -> "stack overflow (potentially due to infinite recursion)"
            exceptionName == NullPointerException::class.java.name -> "null reference access"
            exceptionName != null -> exceptionName.split('.').last()
            else -> it
        }
    }
}
