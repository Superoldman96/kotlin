// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_EXPRESSION -UNUSED_PARAMETER -UNUSED_VARIABLE -NOTHING_TO_INLINE -NON_LOCAL_RETURN_NOT_ALLOWED

inline fun <R> inlineFun(p: () -> R) {
    val s = object {

        val z = p()

        fun a() {
            p()
        }
    }
}

/* GENERATED_FIR_TAGS: anonymousObjectExpression, functionDeclaration, functionalType, inline, localProperty,
nullableType, propertyDeclaration, typeParameter */
