// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
package d

<!WRONG_MODIFIER_TARGET!>override<!> val f : ()-> Int = { 12 }

fun test() {
    f()
}

var g: Int = 1
    <!WRONG_MODIFIER_CONTAINING_DECLARATION!>protected<!> set(i: Int) {}

/* GENERATED_FIR_TAGS: functionDeclaration, functionalType, integerLiteral, lambdaLiteral, override, propertyDeclaration,
setter */
