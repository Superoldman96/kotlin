// Auto-generated by GenerateSteppedRangesCodegenTestData. Do not edit!
// WITH_STDLIB
// LANGUAGE: +RangeUntilOperator
@file:OptIn(ExperimentalStdlibApi::class)
import kotlin.test.*

fun box(): String {
    val uintList = mutableListOf<UInt>()
    for (i in ((1u..<11u).reversed() step 2).reversed() step 3) {
        uintList += i
    }
    assertEquals(listOf(2u, 5u, 8u), uintList)

    val ulongList = mutableListOf<ULong>()
    for (i in ((1uL..<11uL).reversed() step 2L).reversed() step 3L) {
        ulongList += i
    }
    assertEquals(listOf(2uL, 5uL, 8uL), ulongList)

    return "OK"
}