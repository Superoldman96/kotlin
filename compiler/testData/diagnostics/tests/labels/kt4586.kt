// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE
//KT-4586 this@ does not work for builders

fun string(init: StringBuilder.() -> Unit): String{
    val answer = StringBuilder()
    answer.init()
    return answer.toString()
}

val str = string l@{
    append("hello, ")

    val sub = string {
        append("world!")
        this@l.append(this)
    }
}

/* GENERATED_FIR_TAGS: flexibleType, functionDeclaration, functionalType, javaFunction, lambdaLiteral, localProperty,
propertyDeclaration, stringLiteral, thisExpression, typeWithExtension */
