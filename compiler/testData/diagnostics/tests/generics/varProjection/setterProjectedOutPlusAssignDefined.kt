// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNREACHABLE_CODE
interface Tr<T> {
    var v: T
}

class C {
    operator fun plusAssign(c: C) {}
}

fun test(t: Tr<out C>) {
    // No error because no real assignment happens
    t.v += null!!
}

/* GENERATED_FIR_TAGS: capturedType, checkNotNullCall, classDeclaration, functionDeclaration, interfaceDeclaration,
nullableType, operator, outProjection, propertyDeclaration, typeParameter */
