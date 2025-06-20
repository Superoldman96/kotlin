// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_EXPRESSION

open class A
class B: A()

fun A.foo() {}
fun B.foo() {} // more specific

fun bar(a: Any) {}
fun bar(a: Int) {}  // more specific

fun test() {
    B::foo
    ::bar
}

/* GENERATED_FIR_TAGS: callableReference, classDeclaration, funWithExtensionReceiver, functionDeclaration */
