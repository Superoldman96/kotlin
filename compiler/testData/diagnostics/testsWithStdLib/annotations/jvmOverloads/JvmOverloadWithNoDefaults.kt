// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
class C {
    <!OVERLOADS_WITHOUT_DEFAULT_ARGUMENTS!>@kotlin.jvm.JvmOverloads<!> constructor() {
    }

    <!OVERLOADS_WITHOUT_DEFAULT_ARGUMENTS!>@kotlin.jvm.JvmOverloads<!> fun foo(s: String) {}
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, secondaryConstructor */
