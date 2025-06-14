// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER -UNCHECKED_CAST

internal class Demo() {
    suspend operator fun <T> invoke(name: String, block: suspend () -> T): T {
        TODO()
    }
}

suspend fun demo(callback: suspend () -> Unit) = when {
    true -> {
        val demo = Demo()
        demo("test") { callback() } // Before the fix: "Suspend function 'invoke' should be called only from a coroutine or another suspend function"
    }
    else -> TODO()
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, functionalType, lambdaLiteral, localProperty, nullableType,
operator, primaryConstructor, propertyDeclaration, stringLiteral, suspend, typeParameter, whenExpression */
