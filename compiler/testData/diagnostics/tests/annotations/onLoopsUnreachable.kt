// RUN_PIPELINE_TILL: FRONTEND
// LANGUAGE: -ProhibitSimplificationOfNonTrivialConstBooleanExpressions
fun test() {
    @ann
    while (<!NON_TRIVIAL_BOOLEAN_CONSTANT!>2 > 1<!>) {}

    @ann
    <!UNREACHABLE_CODE!>do {} while (<!NON_TRIVIAL_BOOLEAN_CONSTANT!>2 > 1<!>)<!>

    @ann
    <!UNREACHABLE_CODE!>for (i in 1..2) {}<!>
}

annotation class ann

/* GENERATED_FIR_TAGS: annotationDeclaration, comparisonExpression, doWhileLoop, forLoop, functionDeclaration,
integerLiteral, localProperty, propertyDeclaration, rangeExpression, whileLoop */
