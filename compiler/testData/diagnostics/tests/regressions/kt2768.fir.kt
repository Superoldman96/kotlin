// RUN_PIPELINE_TILL: FRONTEND
fun <T> assertEquals(a: T, b: T) {
    if (a != b) throw AssertionError("$a != $b")
}

fun main() {
    val bytePos = 128.toByte() // Byte.MAX_VALUE + 1
    assertEquals(-128, bytePos.toInt()) // correct, wrapped to Byte.MIN_VALUE

    val byteNeg: Byte = <!INITIALIZER_TYPE_MISMATCH!>-bytePos<!> // should not compile, byteNeg should be Int
    assertEquals(128, byteNeg.toInt()) // passes, should not be possible

    val shortPos = 32768.toShort() // Short.MAX_VALUE + 1
    assertEquals(-32768, shortPos.toInt()) // correct, wrapped to Short.MIN_VALUE

    val shortNeg: Short = <!INITIALIZER_TYPE_MISMATCH!>-shortPos<!> // should not compile, shortNeg should be Int
    assertEquals(32768, shortNeg.toInt()) // passes, should not be possible

    (-128).toByte()
    -128.toByte()
}

/* GENERATED_FIR_TAGS: equalityExpression, functionDeclaration, ifExpression, integerLiteral, localProperty,
nullableType, propertyDeclaration, stringLiteral, typeParameter, unaryExpression */
