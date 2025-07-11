// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// CHECK_TYPE

package a
//+JDK
import java.util.*
import checkSubtype

fun <A> cons(x: A, xs: List<A>): List<A> = xs

fun <B> nil(): List<B> = arrayList()

fun test() {
    val xs = cons(1, nil())
    val xs1 = cons("", nil())
    val xs2 = cons(1, nil<Any>())

    checkSubtype<List<Int>>(xs)
    checkSubtype<List<String>>(xs1)
    checkSubtype<List<Any>>(xs2)
}


// ---------------------
// copy from kotlin util

fun <T> arrayList(vararg values: T) : ArrayList<T> = values.toCollection(ArrayList<T>(values.size))

fun <T, C: MutableCollection<in T>> Array<T>.toCollection(result: C) : C {
    for (element in this) result.add(element)
    return result
}

/* GENERATED_FIR_TAGS: capturedType, classDeclaration, flexibleType, forLoop, funWithExtensionReceiver,
functionDeclaration, functionalType, inProjection, infix, integerLiteral, javaFunction, localProperty, nullableType,
outProjection, propertyDeclaration, stringLiteral, thisExpression, typeConstraint, typeParameter, typeWithExtension,
vararg */
