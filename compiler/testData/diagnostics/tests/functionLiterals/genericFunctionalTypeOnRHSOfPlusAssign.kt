// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER

fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> = TODO()
operator fun <T> Collection<T>.plus(elements: Iterable<T>): List<T> = TODO()

fun stringCollection(): Collection<String> = TODO()

fun <K> test(c: Collection<K>) {
    var variants = stringCollection()
    variants += variants.filter { true }
}

/* GENERATED_FIR_TAGS: additiveExpression, assignment, funWithExtensionReceiver, functionDeclaration, functionalType,
lambdaLiteral, localProperty, nullableType, operator, propertyDeclaration, typeParameter */
