// RUN_PIPELINE_TILL: FRONTEND
// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions
// DIAGNOSTICS: -UNUSED_PARAMETER

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.EXPRESSION)
annotation class Ann(vararg val s: String, val x: Int)

fun withVararg(vararg s: String) {}
fun foo() {}

fun test_fun(s: String, arr: Array<String>) {
    withVararg(<!TYPE_MISMATCH!>arr<!>) // Error
    withVararg(*arr) // OK
    withVararg(s = arr) // OK
    withVararg(s = *<!REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION!>arr<!>) // Warning

    withVararg(s) // OK
    withVararg(s = <!ASSIGNING_SINGLE_ELEMENT_TO_VARARG_IN_NAMED_FORM_FUNCTION_ERROR, TYPE_MISMATCH!>s<!>) // Error
}

fun test_ann(s: String, arr: Array<String>) {
    @Ann(<!TYPE_MISMATCH, TYPE_MISMATCH!>[""]<!>, x = 1)
    foo()
    @Ann(*[""], x = 1)
    foo()
    @Ann(s = [""], x = 1)
    foo()
    @Ann(s = *<!REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_ANNOTATION!>[""]<!>, x = 1)
    foo()

    @Ann("", x = 1)
    foo()
    @Ann(s = <!ASSIGNING_SINGLE_ELEMENT_TO_VARARG_IN_NAMED_FORM_ANNOTATION_ERROR, TYPE_MISMATCH!>""<!>, x = 1)
    foo()
}

/* GENERATED_FIR_TAGS: annotationDeclaration, collectionLiteral, functionDeclaration, integerLiteral, outProjection,
primaryConstructor, propertyDeclaration, stringLiteral, vararg */
