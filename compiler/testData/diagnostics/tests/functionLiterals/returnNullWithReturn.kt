// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
package m

interface Element

fun test(handlers: Map<String, Element.()->Unit>) {

    handlers.getOrElse("name", l@ { return@l null })
}

fun <K,V> Map<K,V>.getOrElse(key: K, defaultValue: ()-> V) : V = throw Exception("$key $defaultValue")

/* GENERATED_FIR_TAGS: funWithExtensionReceiver, functionDeclaration, functionalType, interfaceDeclaration,
lambdaLiteral, nullableType, stringLiteral, typeParameter */
