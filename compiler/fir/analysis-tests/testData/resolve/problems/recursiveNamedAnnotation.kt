// RUN_PIPELINE_TILL: BACKEND
// FILE: AliasFor.java

public @interface AliasFor {
    @AliasFor(value = "attribute")
    String value() default "";

    @AliasFor(value = "value")
    String attribute() default "";
}

// FILE: Service.java
public @interface Service {
    @AliasFor(value = "component")
    String value() default "";
}

// FILE: Annotated.kt

@Service(value = "Your")
class My

/* GENERATED_FIR_TAGS: classDeclaration, javaType, stringLiteral */
