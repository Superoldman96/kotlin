// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// CHECK_TYPE

fun <T, R> foo(first: () -> T, second: (T) -> R): R = throw Exception()
fun test() {
    val r = foo( { 4 }, { "${it + 1}" } )
    r checkType { _<String>() }
}

/* GENERATED_FIR_TAGS: additiveExpression, classDeclaration, funWithExtensionReceiver, functionDeclaration,
functionalType, infix, integerLiteral, lambdaLiteral, localProperty, nullableType, propertyDeclaration, typeParameter,
typeWithExtension */
