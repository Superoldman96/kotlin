// RUN_PIPELINE_TILL: FRONTEND
class Outer {
    inner class Inner {
        annotation <!NESTED_CLASS_NOT_ALLOWED("Annotation class")!>class TestNestedAnnotation<!>
    }
}