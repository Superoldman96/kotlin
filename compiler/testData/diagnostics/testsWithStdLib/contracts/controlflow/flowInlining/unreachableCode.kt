// RUN_PIPELINE_TILL: BACKEND
// LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// OPT_IN: kotlin.contracts.ExperimentalContracts
// DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

import kotlin.contracts.*

inline fun <T> myRun(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

fun throwInLambda(): Int {
    <!UNREACHABLE_CODE!>val x =<!> myRun { throw java.lang.IllegalArgumentException(); <!UNREACHABLE_CODE!>42<!> }
    <!UNREACHABLE_CODE!>return x<!>
}

/* GENERATED_FIR_TAGS: contractCallsEffect, contracts, functionDeclaration, functionalType, inline, integerLiteral,
javaFunction, lambdaLiteral, localProperty, nullableType, propertyDeclaration, typeParameter */
