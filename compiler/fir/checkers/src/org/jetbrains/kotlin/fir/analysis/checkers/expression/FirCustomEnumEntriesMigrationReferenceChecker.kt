/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers.expression

import org.jetbrains.kotlin.builtins.StandardNames
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.diagnostics.reportOn
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess
import org.jetbrains.kotlin.fir.isEnabled
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeResolutionResultOverridesOtherToPreserveCompatibility

object FirCustomEnumEntriesMigrationReferenceChecker : FirCallableReferenceAccessChecker(MppCheckerKind.Common) {
    context(context: CheckerContext, reporter: DiagnosticReporter)
    override fun check(expression: FirCallableReferenceAccess) {
        if (LanguageFeature.PrioritizedEnumEntries.isEnabled()) return
        if (expression.calleeReference.name == StandardNames.ENUM_ENTRIES &&
            expression.nonFatalDiagnostics.any { it is ConeResolutionResultOverridesOtherToPreserveCompatibility }
        ) {
            reporter.reportOn(expression.source, FirErrors.DEPRECATED_ACCESS_TO_ENUM_ENTRY_PROPERTY_AS_REFERENCE)
        }
    }
}
