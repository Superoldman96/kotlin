// RUN_PIPELINE_TILL: BACKEND
// ISSUE: KT-24807

fun testNullableAnyToBoolean(x: Any?) {
    if (x !is Boolean) return
    return when (x) {
        true -> Unit
        false -> Unit
    }
}

fun testAnyToBoolean(x: Any) {
    if (x !is Boolean) return
    return when (x) {
        true -> Unit
        false -> Unit
    }
}

/* GENERATED_FIR_TAGS: equalityExpression, functionDeclaration, ifExpression, isExpression, nullableType, smartcast,
whenExpression, whenWithSubject */
