// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// API_VERSION: 1.5
// LANGUAGE: +JvmRecordSupport
// SCOPE_DUMP: MyRecord:x

// FILE: MyRecord.java
public record MyRecord(String x) {
    public String x() {
        System.out.println("hello");
        return x;
    }
}

// FILE: main.kt

fun takeString(s: String) {}

fun foo(mr: MyRecord) {
    takeString(mr.x)
    takeString(mr.x())
}

/* GENERATED_FIR_TAGS: flexibleType, functionDeclaration, javaFunction, javaProperty, javaType */
