// LANGUAGE: +StrictJavaNullabilityAssertions
// TARGET_BACKEND: JVM
// IGNORE_BACKEND_K1: JVM_IR
// WITH_STDLIB

// Note: this fails on JVM_IR because of KT-36347.
// It requires potentially breaking changes in FE, so please, don't touch it until the language design decision.

// FILE: box.kt
import kotlin.test.*

fun box(): String {
    // Sanity check to make sure there IS an exception even when not in a for-loop
    try {
        val (index, i) = JImpl().arrayOfNotNull().withIndex().first()
        return "Fail: should throw on get()"
    } catch (e: NullPointerException) {}

    try {
        for ((index, i) in JImpl().arrayOfNotNull().withIndex()) {
            return "Fail: should throw on get() in loop header"
        }
    }
    catch (e: NullPointerException) {}
    return "OK"
}

interface J {
    fun arrayOfNotNull(): Array<Int>
}

// FILE: JImpl.java
public class JImpl implements J {
    // The only way to get @EnhancedNullability on the array element type (Int) is to override a Kotlin function that
    // returns `Array<Int>` (where Int is not nullable). `@NotNull Integer[]` makes the array not nullable, not String.
    @Override
    public Integer[] arrayOfNotNull() {
        return new Integer[] { null };
    }
}
