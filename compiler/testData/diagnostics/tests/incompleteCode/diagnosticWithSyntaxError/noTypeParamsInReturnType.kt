// RUN_PIPELINE_TILL: FRONTEND
package b

fun <T, R> foo(map: Map<T, R>) : R = throw Exception()

fun <F, G> getMap() : Map<F, G> = throw Exception()

fun bar123() {
    <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>foo<!>(<!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>getMap<!>(
<!SYNTAX!><!>}

/* GENERATED_FIR_TAGS: functionDeclaration, nullableType, typeParameter */
