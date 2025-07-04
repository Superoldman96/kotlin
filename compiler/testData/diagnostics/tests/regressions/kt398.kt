// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// KT-398 Internal error when property initializes with function

class X<T>() {
    val check = { a : Any -> a is <!CANNOT_CHECK_FOR_ERASED!>T<!> }
}

fun box() : String {
    if(X<String>().check(10)) return "fail"
    if(!X<String>().check("lala")) return "fail"
    return "OK"
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, ifExpression, integerLiteral, isExpression, lambdaLiteral,
nullableType, primaryConstructor, propertyDeclaration, stringLiteral, typeParameter */
