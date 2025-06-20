// RUN_PIPELINE_TILL: FRONTEND
// FILE: 1.kt
package simpleObject

typealias SimpleObject = TestCase

object InvokableObject {
    operator fun invoke() {}
}

object TestCase {
    val propertyLikeClbl = InvokableObject
}

// FILE: 2.kt
import simpleObject.SimpleObject.propertyLikeClbl

/* GENERATED_FIR_TAGS: functionDeclaration, objectDeclaration, operator, propertyDeclaration, typeAliasDeclaration */
