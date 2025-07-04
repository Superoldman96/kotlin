// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// CHECK_TYPE
// DIAGNOSTICS: -UNUSED_EXPRESSION
// FILE: a.kt

package first

import checkSubtype

class A

fun A.foo() {}
fun A.bar() {}
fun A.baz() {}

// FILE: b.kt

package other

import kotlin.reflect.KFunction1

import first.A
import first.foo
import checkSubtype

fun main() {
    val x = first.A::foo
    first.A::<!UNRESOLVED_REFERENCE!>bar<!>
    A::<!UNRESOLVED_REFERENCE!>baz<!>

    checkSubtype<KFunction1<A, Unit>>(x)
}

/* GENERATED_FIR_TAGS: callableReference, classDeclaration, funWithExtensionReceiver, functionDeclaration,
functionalType, infix, localProperty, nullableType, propertyDeclaration, typeParameter, typeWithExtension */
