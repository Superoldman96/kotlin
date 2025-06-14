// RUN_PIPELINE_TILL: BACKEND
open class Inv<T, R>(x: T, r: R)

typealias Alias<X> = Inv<X, Inv<X, X>>

class InvImpl : Alias<String>("", Inv("", ""))

/* GENERATED_FIR_TAGS: classDeclaration, nullableType, primaryConstructor, stringLiteral, typeAliasDeclaration,
typeAliasDeclarationWithTypeParameter, typeParameter */
