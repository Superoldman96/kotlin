// RUN_PIPELINE_TILL: FRONTEND
fun foo(arg: Int?) {
    val x = arg
    if (x != null) {
        arg.hashCode()
    }
    val y: Any? = arg
    if (y != null) {
        arg<!UNSAFE_CALL!>.<!>hashCode()
    }
    val yy: Any?
    yy = arg
    if (yy != null) {
        arg.hashCode()
    }
    var z = arg
    z = z?.let { 42 }
    if (z != null) {
        arg<!UNSAFE_CALL!>.<!>hashCode()
    }
}

/* GENERATED_FIR_TAGS: assignment, equalityExpression, functionDeclaration, ifExpression, integerLiteral, lambdaLiteral,
localProperty, nullableType, propertyDeclaration, safeCall, smartcast */
