// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// LANGUAGE: +Coroutines
// SKIP_TXT

import kotlin.reflect.KSuspendFunction0

fun test(c: KSuspendFunction0<Unit>) {
    <!ILLEGAL_SUSPEND_FUNCTION_CALL!>c<!>()
}

/* GENERATED_FIR_TAGS: functionDeclaration */
