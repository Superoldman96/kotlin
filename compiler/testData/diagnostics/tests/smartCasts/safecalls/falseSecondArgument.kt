// RUN_PIPELINE_TILL: FRONTEND

fun calc(x: List<String>?, y: Int?): Int {
    x?.subList(y!! - 1, <!DEBUG_INFO_SMARTCAST!>y<!>)
    // y!! above should not provide smart cast here
    return <!TYPE_MISMATCH!>y<!>
}

/* GENERATED_FIR_TAGS: additiveExpression, checkNotNullCall, functionDeclaration, integerLiteral, nullableType, safeCall,
smartcast */
