// ISSUE: KT-53740

// IGNORE_BACKEND: ANY
// IGNORE_IR_DESERIALIZATION_TEST: NATIVE
// REASON: red code (see corresponding diagnostic test)
// IGNORE_IR_DESERIALIZATION_TEST: JS_IR
// ^^^ Source code is not compiled in JS.

fun box(): String {
    parallelBuild(
        {
            setTypeVariable(TargetType())
        },
        {
            consumeDifferentType(getTypeVariable())
        }
    )
    return "OK"
}




class TargetType
class DifferentType

fun consumeDifferentType(value: DifferentType) {}

class Buildee<TV> {
    fun setTypeVariable(value: TV) { storage = value }
    fun getTypeVariable(): TV = storage
    private var storage: TV = TargetType() as TV
}

fun <PTV> parallelBuild(
    instructionsA: Buildee<PTV>.(PTV) -> Unit,
    instructionsB: Buildee<PTV>.(PTV) -> Unit
): Buildee<PTV> {
    val value = TargetType() as PTV
    return Buildee<PTV>().apply {
        instructionsA(value)
        instructionsB(value)
    }
}
