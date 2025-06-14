// RUN_PIPELINE_TILL: BACKEND
// DUMP_CFG

val x4: (String) -> Unit = run {
    return@run (lambda@{ foo: String ->
        bar(foo)
    })
}

fun bar(s: String) {}
fun <R> run(block: () -> R): R = block()

/* GENERATED_FIR_TAGS: functionDeclaration, functionalType, lambdaLiteral, nullableType, propertyDeclaration,
typeParameter */
