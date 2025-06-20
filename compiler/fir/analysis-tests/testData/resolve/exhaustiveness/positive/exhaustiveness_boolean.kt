// RUN_PIPELINE_TILL: FRONTEND
fun test_1(b: Boolean) {
    val x = <!NO_ELSE_IN_WHEN!>when<!> (b) {
        true -> 1
    }
    val y = when (b) {
        true -> 1
        false -> 2
    }
    val z = when (b) {
        true -> 1
        else -> 2
    }
}

fun test_2(b: Boolean?) {
    val x = <!NO_ELSE_IN_WHEN!>when<!> (b) {
        true -> 1
        false -> 2
    }
    val y = when (b) {
        true -> 1
        false -> 2
        null -> 3
    }
}

/* GENERATED_FIR_TAGS: equalityExpression, functionDeclaration, integerLiteral, localProperty, nullableType,
propertyDeclaration, smartcast, whenExpression, whenWithSubject */
