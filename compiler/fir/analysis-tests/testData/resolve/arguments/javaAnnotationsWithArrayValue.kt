// RUN_PIPELINE_TILL: FRONTEND
// FILE: Ann.java

public @interface Ann {
    String[] value();
}

// FILE: main.kt

@Ann("a", "b")
fun test_1() {}

@Ann(<!ARGUMENT_TYPE_MISMATCH!>arrayOf("a", "b")<!>)
fun test_2() {}

@Ann(*arrayOf("a", "b"))
fun test_3() {}

/* GENERATED_FIR_TAGS: collectionLiteral, functionDeclaration, javaType, stringLiteral */
