// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// DIAGNOSTICS: -DEPRECATION

class `_`<`__`> {
    fun testTypeArgument(x: List<<!UNDERSCORE_USAGE_WITHOUT_BACKTICKS!>__<!>>) = x
    fun testTypeArgument2(x: List<`__`>) = x
}

fun <!UNDERSCORE_USAGE_WITHOUT_BACKTICKS!>_<!><Any>.testTypeConstructor() {}
fun `_`<Any>.testTypeConstructor2() {}

val testConstructor = <!UNDERSCORE_USAGE_WITHOUT_BACKTICKS!>_<!><Any>()
val testConstructor2 = `_`<Any>()

/* GENERATED_FIR_TAGS: classDeclaration, funWithExtensionReceiver, functionDeclaration, nullableType,
propertyDeclaration, typeParameter */
