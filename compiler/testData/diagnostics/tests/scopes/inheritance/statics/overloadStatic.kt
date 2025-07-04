// DISABLE_JAVA_FACADE
// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE
// FILE: A.java
public class A {
    static void foo() {}
    static int bar() { return 1; }
}

// FILE: B.java
public class B extends A {
    static void foo() {}
    static long bar() { return 1; }
}

// FILE: 1.kt
class E: B() {
    init {
        foo()
        val a: Long = bar()
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, init, javaFunction, javaType, localProperty, propertyDeclaration */
