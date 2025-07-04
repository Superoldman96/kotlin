// RUN_PIPELINE_TILL: BACKEND
// DIAGNOSTICS: -UNUSED_VARIABLE

// FILE: Function.java

public interface Function<Param, Result> {
    Result fun(Param param);
}

// FILE: AdapterProcessor.java

public class AdapterProcessor<T, S> {
  public AdapterProcessor(Function<? super T, ? extends S> conversion) {}
}


// FILE: main.kt

interface PsiMethod {
    val containingClass: PsiClass?
}

interface PsiClass

fun test() {
    val processor = AdapterProcessor<PsiMethod, PsiClass>(
        Function { method: PsiMethod? -> method?.containingClass }
    )
}

/* GENERATED_FIR_TAGS: flexibleType, functionDeclaration, interfaceDeclaration, javaFunction, javaType, lambdaLiteral,
localProperty, nullableType, propertyDeclaration, safeCall */
