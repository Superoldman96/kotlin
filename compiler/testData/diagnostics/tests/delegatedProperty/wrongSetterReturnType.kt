// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER

import kotlin.reflect.KProperty

var b: Int by Delegate()

class Delegate {
    operator fun getValue(t: Any?, p: KProperty<*>): Int {
      return 1
    }

    operator fun setValue(t: Any?, p: KProperty<*>, i: Int): Int {
      return i
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, integerLiteral, nullableType, operator,
propertyDeclaration, propertyDelegate, setter, starProjection */
