// RUN_PIPELINE_TILL: FRONTEND
// CHECK_TYPE

fun foo1(a: Int?, b: Array<Array<Int>>) {
    b[a!!][a<!UNNECESSARY_NOT_NULL_ASSERTION!>!!<!>] = <!DEBUG_INFO_SMARTCAST!>a<!>
    checkSubtype<Int>(<!DEBUG_INFO_SMARTCAST!>a<!>)
}

fun foo2(a: Int?, b: Array<Array<Int>>) {
    b[0][a!!] = <!DEBUG_INFO_SMARTCAST!>a<!>
    checkSubtype<Int>(<!DEBUG_INFO_SMARTCAST!>a<!>)
}

/* GENERATED_FIR_TAGS: assignment, checkNotNullCall, classDeclaration, funWithExtensionReceiver, functionDeclaration,
functionalType, infix, integerLiteral, nullableType, smartcast, typeParameter, typeWithExtension */
