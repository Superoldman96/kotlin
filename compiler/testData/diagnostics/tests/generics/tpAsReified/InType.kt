// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
class C<<!REIFIED_TYPE_PARAMETER_NO_INLINE!>reified<!> T>

fun <A> main(p1: C<A>, p2: C<Int>) {
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, nullableType, reified, typeParameter */
