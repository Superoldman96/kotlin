// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_ANONYMOUS_PARAMETER

fun main() {
    fun foo(m: Map<String, (Array<Int>) -> Unit>) {}
    fun mySort(a: Array<Int>) {}
    foo(m = mapOf(
        "mySort" to ::mySort,
        "mergeSort" to { a: Array<Int> -> }
    ))
}

/* GENERATED_FIR_TAGS: callableReference, functionDeclaration, lambdaLiteral, localFunction, stringLiteral */
