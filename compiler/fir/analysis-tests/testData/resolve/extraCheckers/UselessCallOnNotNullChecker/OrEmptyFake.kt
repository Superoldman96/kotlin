// RUN_PIPELINE_TILL: BACKEND
// WITH_STDLIB

val list: List<String>? = null
val empty = list.orEmpty()

/* GENERATED_FIR_TAGS: nullableType, propertyDeclaration */
