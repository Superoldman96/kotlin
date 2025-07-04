// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_VARIABLE

val bar = fun() {}
val bas = fun() {}

fun gar(p: Any?) = fun() {}
fun gas(p: Any?) = fun() {}

fun outer() {
    val bar = fun() {}
    val bas = fun() {}

    fun gar(p: Any?) = fun() {}
    fun gas(p: Any?) = fun() {}

    gar(fun() {})
    gar(fun() {})
}

/* GENERATED_FIR_TAGS: anonymousFunction, functionDeclaration, localFunction, localProperty, nullableType,
propertyDeclaration */
