// RUN_PIPELINE_TILL: FRONTEND
package a

fun foo() : Int {
    try {
        doSmth()
    }
    catch (e: Exception) {
        <!UNREACHABLE_CODE!>return<!> <!TYPE_MISMATCH!>""<!>
    }
    finally {
        return <!TYPE_MISMATCH!>""<!>
    }
}

fun bar() : Int =
    try <!TYPE_MISMATCH!>{
        doSmth()
    }<!>
    catch (e: Exception) <!TYPE_MISMATCH!>{
        ""
    }<!>
    finally {
        ""
    }


fun doSmth() {}

/* GENERATED_FIR_TAGS: functionDeclaration, localProperty, propertyDeclaration, stringLiteral, tryExpression */
