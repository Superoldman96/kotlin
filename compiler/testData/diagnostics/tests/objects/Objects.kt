// RUN_PIPELINE_TILL: FRONTEND
package toplevelObjectDeclarations

open class Foo(y: Int) {
    open fun foo(): Int = 1
}

class T : <!SUPERTYPE_NOT_INITIALIZED!>Foo<!> {}

object A : <!SUPERTYPE_NOT_INITIALIZED!>Foo<!> {
    val x: Int = 2

    fun test(): Int {
        return x + foo()
    }
}

object B : <!SINGLETON_IN_SUPERTYPE!>A<!> {}

val c = object : <!SUPERTYPE_NOT_INITIALIZED!>Foo<!> {}

val x = A.foo()

val y = object : Foo(x) {
    init {
        x + 12
    }

    override fun foo(): Int = 1
}

val z = y.foo()

/* GENERATED_FIR_TAGS: additiveExpression, anonymousObjectExpression, classDeclaration, functionDeclaration, init,
integerLiteral, objectDeclaration, override, primaryConstructor, propertyDeclaration */
