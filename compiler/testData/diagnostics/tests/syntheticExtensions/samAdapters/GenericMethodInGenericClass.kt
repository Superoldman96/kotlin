// DISABLE_JAVA_FACADE
// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// JAVAC_EXPECTED_FILE

// FILE: KotlinFile.kt
fun foo(javaClass: JavaClass<Int>) {
    val a: String = javaClass.doSomething1("", 1) { p: String -> p }
    val b: String = javaClass.doSomething2("", 1, true) { p: Int -> p }
}

// FILE: JavaClass.java
public class JavaClass<X> {
    public <T> T doSomething1(T t, X x, I<T> i) { return i.run(t); }
    public <T> T doSomething2(T t, X x, boolean p, I<X> i) { return i.run(t); }
}

// FILE: I.java
interface I<T> {
    T run(T t);
}

/* GENERATED_FIR_TAGS: flexibleType, functionDeclaration, integerLiteral, javaType, lambdaLiteral, localProperty,
propertyDeclaration, samConversion, stringLiteral */
