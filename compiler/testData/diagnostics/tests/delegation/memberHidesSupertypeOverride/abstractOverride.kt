// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
public interface Base {
    fun test() = "OK"
}


abstract class Impl : Base {
    override abstract fun test(): String
}

class Delegate : Base


fun box(): String {
    object : Impl(), Base by Delegate() {
    }

    return "OK"
}

/* GENERATED_FIR_TAGS: anonymousObjectExpression, classDeclaration, functionDeclaration, inheritanceDelegation,
interfaceDeclaration, override, stringLiteral */
