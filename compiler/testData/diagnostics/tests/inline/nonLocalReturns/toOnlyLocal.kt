// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL

inline fun <R> toOnlyLocal(crossinline p: () -> R) {
    p()
}

inline fun <R> inlineAll(p: () -> R) {
    toOnlyLocal(<!NON_LOCAL_RETURN_NOT_ALLOWED!>p<!>)
}

/* GENERATED_FIR_TAGS: crossinline, functionDeclaration, functionalType, inline, nullableType, typeParameter */
