// RUN_PIPELINE_TILL: BACKEND
// DUMP_CFG
class Foo {
    init {
        val x = 1
    }
}

class Bar {
    init {
        val x = 1
        throw Exception()
        val y = 2
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, init, integerLiteral, localProperty, propertyDeclaration */
