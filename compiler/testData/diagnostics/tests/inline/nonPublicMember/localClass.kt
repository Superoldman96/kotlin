// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_EXPRESSION -UNUSED_PARAMETER -NOTHING_TO_INLINE

public class Z {
    private val privateProperty = 11;

    public fun privateFun() {

        class Local {
            public inline fun a() {
                privateProperty
            }
        }

    }
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, inline, integerLiteral, localClass, propertyDeclaration */
