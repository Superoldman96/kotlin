// RUN_PIPELINE_TILL: FRONTEND
// DIAGNOSTICS: -UNUSED_PARAMETER
// LANGUAGE: +RequiredPrimaryConstructorDelegationCallInEnums

enum class Enum1(val a: String) {
    A;
    <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>constructor()<!>
}

enum class Enum2(val a: String) {
    A, B;
    constructor(): this("")
}

enum class Enum3(val a: String = "") {
    A, B, C;
    <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>constructor()<!>
}

enum class Enum4(val a: String = "") {
    A, B, C;
    constructor(): <!CYCLIC_CONSTRUCTOR_DELEGATION_CALL!>this<!>()
}

enum class Enum5(val a: String = "") {
    A, B, C;
    constructor(): this(a = "")
}

enum class Enum6(val a: String = "") {
    A, B, C;
}

enum class Enum7(val a: String) {
    A, B, C;
    constructor(): this(10)
    constructor(x: Int): this("")
}

enum class Enum8(val a: String) {
    A, B, C;
    constructor(): this(10)
    <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>constructor(x: Int)<!>
}

/* GENERATED_FIR_TAGS: enumDeclaration, enumEntry, integerLiteral, primaryConstructor, propertyDeclaration,
secondaryConstructor, stringLiteral */
