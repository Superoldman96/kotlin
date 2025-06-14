// RUN_PIPELINE_TILL: BACKEND
// DUMP_CFG
open class A(open val x: Any)

class B(x: Any) : A(x) {
    fun test_1() {
        if (x is String) {
            x.length
        }
    }
}

fun test_2(b: B) {
    if (b.x is String) {
        b.x.length
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, ifExpression, isExpression, primaryConstructor,
propertyDeclaration, smartcast */
