// RUN_PIPELINE_TILL: FRONTEND
// DIAGNOSTICS: -UNUSED_PARAMETER

import kotlin.reflect.KProperty

class A {
    val c: Int by <!DELEGATE_SPECIAL_FUNCTION_NONE_APPLICABLE!>Delegate()<!>
}

class Delegate {
    fun getValue(t: Int, p: KProperty<*>): Int {
        return 1
    }

    fun getValue(t: String, p: KProperty<*>): Int {
        return 1
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, integerLiteral, propertyDeclaration, propertyDelegate,
starProjection */
