// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// KT-258 Support equality constraints in type inference

import java.util.*

fun test() {
  val attributes : HashMap<String, String> = HashMap()
  attributes["href"] = "1" // inference fails, but it shouldn't
}

operator fun <K, V> MutableMap<K, V>.set(key : K, value : V) {}//= this.put(key, value)

/* GENERATED_FIR_TAGS: assignment, funWithExtensionReceiver, functionDeclaration, javaFunction, localProperty,
nullableType, operator, propertyDeclaration, stringLiteral, typeParameter */
