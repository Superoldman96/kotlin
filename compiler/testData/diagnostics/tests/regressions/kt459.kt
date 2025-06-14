// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// KT-459 Type argument inference fails when class names are fully qualified

fun test() {
  val attributes : java.util.HashMap<String, String> = java.util.HashMap() // failure!
  attributes["href"] = "1" // inference fails, but it shouldn't
}

operator fun <K, V> Map<K, V>.set(key : K, value : V) {}//= this.put(key, value)

/* GENERATED_FIR_TAGS: assignment, funWithExtensionReceiver, functionDeclaration, javaFunction, localProperty,
nullableType, operator, propertyDeclaration, stringLiteral, typeParameter */
