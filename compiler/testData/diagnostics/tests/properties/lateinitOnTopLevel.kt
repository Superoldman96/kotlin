// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// LANGUAGE: +LateinitTopLevelProperties

object Delegate {
    operator fun getValue(instance: Any?, property: Any) : String = ""
    operator fun setValue(instance: Any?, property: Any, value: String) {}
}

lateinit var testOk: String

<!INAPPLICABLE_LATEINIT_MODIFIER!>lateinit<!> val testErr0: Any
<!INAPPLICABLE_LATEINIT_MODIFIER!>lateinit<!> var testErr1: Int
<!INAPPLICABLE_LATEINIT_MODIFIER!>lateinit<!> var testErr2: Any?
<!INAPPLICABLE_LATEINIT_MODIFIER!>lateinit<!> var testErr3: String = ""
<!INAPPLICABLE_LATEINIT_MODIFIER!>lateinit<!> var testErr4 by Delegate

/* GENERATED_FIR_TAGS: functionDeclaration, lateinit, nullableType, objectDeclaration, operator, propertyDeclaration,
propertyDelegate, setter, stringLiteral */
