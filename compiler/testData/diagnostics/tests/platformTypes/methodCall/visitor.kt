// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// FILE: p/Visitor.java

package p;

public interface Visitor<D> {

}

// FILE: p/Element.java

package p;

import org.jetbrains.annotations.NotNull;

public class Element {
    public <D, R> R accept(@NotNull Visitor<R> visitor, D data) {return null;}
}

// FILE: k.kt

import p.*

fun test(v: Visitor<Nothing>, e: Element) {
    e.accept(v, null)
}

/* GENERATED_FIR_TAGS: flexibleType, functionDeclaration, javaFunction, javaType, nullableType */
