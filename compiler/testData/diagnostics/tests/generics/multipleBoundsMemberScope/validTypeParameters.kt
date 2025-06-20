// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// CHECK_TYPE

interface A {
    fun <T, E> foo(): E
}

interface B {
    fun <Q, W> foo(): W
}

fun <T> test(x: T) where T : B, T : A {
    x.foo<String, Int>().checkType { _<Int>() }
}

/* GENERATED_FIR_TAGS: classDeclaration, funWithExtensionReceiver, functionDeclaration, functionalType, infix,
interfaceDeclaration, lambdaLiteral, nullableType, typeConstraint, typeParameter, typeWithExtension */
