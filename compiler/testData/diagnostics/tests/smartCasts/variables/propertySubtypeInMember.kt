// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
fun bar(s: Any): Int {
    return s.hashCode()
}

class MyClass(var p: Any) {
    fun foo(): Int {
        p = "xyz"
        return bar(p)
    }
}

/* GENERATED_FIR_TAGS: assignment, classDeclaration, functionDeclaration, primaryConstructor, propertyDeclaration,
stringLiteral */
