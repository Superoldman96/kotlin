// RUN_PIPELINE_TILL: FRONTEND
// CHECK_TYPE

fun foo(arr: Array<out Number>): Int {
    @Suppress("UNCHECKED_CAST")
    val result = (arr as Array<Int>)[0]
    checkSubtype<Array<Int>>(<!DEBUG_INFO_SMARTCAST!>arr<!>)
    return result
}

/* GENERATED_FIR_TAGS: asExpression, classDeclaration, funWithExtensionReceiver, functionDeclaration, functionalType,
infix, integerLiteral, localProperty, nullableType, outProjection, propertyDeclaration, smartcast, stringLiteral,
typeParameter, typeWithExtension */
