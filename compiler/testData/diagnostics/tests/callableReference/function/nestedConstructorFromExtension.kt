// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// CHECK_TYPE
// DIAGNOSTICS: -UNUSED_EXPRESSION
import kotlin.reflect.KFunction0

class A {
    class Nested
}
    
fun A.main() {
    ::<!UNRESOLVED_REFERENCE!>Nested<!>
    val y = A::Nested
    
    checkSubtype<KFunction0<A.Nested>>(y)
}

fun Int.main() {
    ::<!UNRESOLVED_REFERENCE!>Nested<!>
    val y = A::Nested

    checkSubtype<KFunction0<A.Nested>>(y)
}

/* GENERATED_FIR_TAGS: callableReference, classDeclaration, funWithExtensionReceiver, functionDeclaration,
functionalType, infix, localProperty, nestedClass, nullableType, propertyDeclaration, typeParameter, typeWithExtension */
