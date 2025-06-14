// RUN_PIPELINE_TILL: FRONTEND
// INFERENCE_HELPERS
// ISSUES: KT-44802, KT-56744
// LANGUAGE: -ForbidInferOfInvisibleTypeAsReifiedVarargOrReturnType
// FILE: a.kt

package foo
import select

private interface PrivateInterface {
    fun foo() {}
}

class A : PrivateInterface
class B : PrivateInterface

fun testSmartcast(x: Any) {
    if (x is A || x is B) {
        <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Any")!>x<!>.<!UNRESOLVED_REFERENCE!>foo<!>()
    }
}

fun testInference(a: A, b: B) {
    val x = <!DEBUG_INFO_EXPRESSION_TYPE("foo.PrivateInterface")!>select(a, b)<!>
    x.foo()
}

// FILE: main.kt

package bar

import foo.A
import foo.B
import select

fun testSmartcast(x: Any) {
    if (x is A || x is B) {
        <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Any")!>x<!>.<!UNRESOLVED_REFERENCE!>foo<!>()
    }
}

fun testInference(a: A, b: B) {
    val x = <!DEBUG_INFO_EXPRESSION_TYPE("foo.PrivateInterface")!>select(a, b)<!>
    x.<!INVISIBLE_MEMBER!>foo<!>()
}

/* GENERATED_FIR_TAGS: capturedType, checkNotNullCall, classDeclaration, disjunctionExpression, functionDeclaration,
ifExpression, integerLiteral, interfaceDeclaration, isExpression, localProperty, nullableType, outProjection,
propertyDeclaration, smartcast, typeParameter, vararg */
