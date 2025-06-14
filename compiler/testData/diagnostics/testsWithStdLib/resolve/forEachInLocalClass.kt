// RUN_PIPELINE_TILL: FRONTEND
// SKIP_TXT
// FIR_IDENTICAL
// FULL_JDK
// CHECK_TYPE
// DIAGNOSTICS: -UNUSED_EXPRESSION -UNUSED_PARAMETER -UNUSED_VARIABLE

// FILE: A.java
import java.util.List;
public class A {
    public static List<String> foo() { return null; }
}

// FILE: main.kt

fun bar(): Int {
    return object {
        fun baz(): Int {
            val strings = A.foo()
            strings.forEach {
                if (it.length == 0) return 1
            }
            return 2
        }
    }.baz()
}

/* GENERATED_FIR_TAGS: anonymousObjectExpression, classDeclaration, equalityExpression, flexibleType,
funWithExtensionReceiver, functionDeclaration, functionalType, ifExpression, infix, integerLiteral, javaFunction,
lambdaLiteral, localProperty, nullableType, propertyDeclaration, typeParameter, typeWithExtension */
