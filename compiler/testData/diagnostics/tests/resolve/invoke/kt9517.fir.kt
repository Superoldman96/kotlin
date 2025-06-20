// RUN_PIPELINE_TILL: BACKEND
//KT-9517 Wrong resolve for invoke convention after smart cast
open class A {
    open val foo: () -> Number = null!!
}

class B: A() {
    override val foo: () -> Int
        get() = null!!
}

fun test(a: A) {
    if (a is B) {
        val foo: Int = a.foo() // B::foo + invoke()
    }
}

/* GENERATED_FIR_TAGS: checkNotNullCall, classDeclaration, functionDeclaration, functionalType, getter, ifExpression,
isExpression, localProperty, override, propertyDeclaration, smartcast */
