// RUN_PIPELINE_TILL: FRONTEND
// NI_EXPECTED_FILE
class Outer {
    class Nested<T>
}

fun nested() = Outer.Nested<Int>()
fun noArguments() = Outer.<!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>Nested<!>()
fun noArgumentsExpectedType(): Outer.Nested<String> = Outer.Nested()
fun manyArguments() = Outer.Nested<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!><String, Int><!>()

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, nestedClass, nullableType, typeParameter */
