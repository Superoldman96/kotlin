// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// LANGUAGE: +EliminateAmbiguitiesOnInheritedSamInterfaces +SamConversionForKotlinFunctions +SamConversionPerArgument
// CHECK_TYPE
// FILE: Fn.java
public interface Fn<T, R> {
    R apply(T t);
}

// FILE: Fn2.java
public interface Fn2<T, R> extends Fn<T, R> {}

// FILE: 1.kt
interface K {
    fun foo(f: Fn<String, Any>): String
    fun foo(f: Fn<Any, Any>): Int

    fun bas(f: Fn<Any, Any>): String
    fun bas(f: Fn<Any, String>): Int

    fun bar(f: Fn<String, Any>): String
    fun bar(f: Fn2<String, Any>): Int
}

fun test(k: K) {
    k.foo { it checkType { _<Any>() }; "" } checkType { _<Int>() }

    k.bas { it checkType { _<Any?>() }; "" } checkType { _<Int>() }

    k.bar { it checkType { _<String>() }; "" } checkType { _<Int>() }
}

/* GENERATED_FIR_TAGS: classDeclaration, flexibleType, funWithExtensionReceiver, functionDeclaration, functionalType,
infix, interfaceDeclaration, javaType, lambdaLiteral, nullableType, samConversion, stringLiteral, typeParameter,
typeWithExtension */
