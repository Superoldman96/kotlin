// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_VARIABLE
// FILE: s/SamConstructor.java
package s;

public class SamConstructor {
    public SamConstructor(Runnable r) {
    }

    public static void foo(Runnable r) {}
}

// FILE: 1.kt
package a

fun SamConstructor(a: () -> Unit) {}

// FILE: 2.kt

package b

import s.SamConstructor
import a.*

fun test() {
    val a: s.SamConstructor = SamConstructor {  }

    val b: s.SamConstructor = SamConstructor(null)

    SamConstructor.foo(null)
}

/* GENERATED_FIR_TAGS: functionDeclaration, functionalType, javaFunction, javaType, lambdaLiteral, localProperty,
nullableType, propertyDeclaration, samConversion */
