// RUN_PIPELINE_TILL: FRONTEND
// WITH_STDLIB

object DelegateTest {
    var result = ""
    val f by lazy {
        result += <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE, TYPECHECKER_HAS_RUN_INTO_RECURSIVE_PROBLEM_IN_AUGMENTED_ASSIGNMENT_ERROR!>f<!>.<!DEBUG_INFO_MISSING_UNRESOLVED!>toString<!>() // Compiler crash
        "hello"
    }
}

/* GENERATED_FIR_TAGS: additiveExpression, assignment, lambdaLiteral, objectDeclaration, propertyDeclaration,
propertyDelegate, stringLiteral */
