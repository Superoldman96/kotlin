// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
class A<T> : <!SUPERTYPE_NOT_A_CLASS_OR_INTERFACE!>T<!> {}

/* GENERATED_FIR_TAGS: classDeclaration, nullableType, typeParameter */
