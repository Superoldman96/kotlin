// RUN_PIPELINE_TILL: FRONTEND
fun useDeclaredVariables() {
    <!INITIALIZER_REQUIRED_FOR_DESTRUCTURING_DECLARATION!>val (a, b)<!>
    <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>a<!>
    <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>b<!>
}

fun checkersShouldRun() {
    <!INITIALIZER_REQUIRED_FOR_DESTRUCTURING_DECLARATION!>val (@A a, _)<!>
}

annotation class A

/* GENERATED_FIR_TAGS: annotationDeclaration, destructuringDeclaration, functionDeclaration, localProperty,
propertyDeclaration, unnamedLocalVariable */
