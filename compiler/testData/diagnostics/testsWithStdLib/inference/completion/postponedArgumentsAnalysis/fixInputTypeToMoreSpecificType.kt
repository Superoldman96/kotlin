// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -CAST_NEVER_SUCCEEDS -UNUSED_PARAMETER -UNCHECKED_CAST

fun <T> materialize() = null as T

val x: Map<String, String> = materialize<List<Map<String, String>>>().fold(mutableMapOf()) { m, x ->
    val (s, action) = x.entries.first()
    m[s] = action
    m
}

/* GENERATED_FIR_TAGS: asExpression, assignment, destructuringDeclaration, functionDeclaration, lambdaLiteral,
localProperty, nullableType, propertyDeclaration, typeParameter */
