// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER

// FILE: JClass.java

public class JClass {
    public <K> void foo(Key<K> key, K value) {}
}

// FILE: test.kt

class Key<T>

fun <S> select(x: S, y: S): S = x

fun <T> setValue(key: Key<T>, value: T, j: JClass) {
    j.foo(key, select(value, null))
}

/* GENERATED_FIR_TAGS: classDeclaration, flexibleType, functionDeclaration, javaFunction, javaType, nullableType,
typeParameter */
