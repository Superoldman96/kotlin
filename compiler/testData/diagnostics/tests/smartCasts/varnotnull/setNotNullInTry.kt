// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// LANGUAGE: +SoundSmartCastsAfterTry

fun bar(arg: Any?) = arg

fun foo() {
    var s: String?
    s = null
    try {
        s = "Test"
    } catch (ex: Exception) {}
    bar(s)
    if (s != null) { }
    s<!UNSAFE_CALL!>.<!>hashCode()
}

/* GENERATED_FIR_TAGS: assignment, equalityExpression, functionDeclaration, ifExpression, localProperty, nullableType,
propertyDeclaration, stringLiteral, tryExpression */
