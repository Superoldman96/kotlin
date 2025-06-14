// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER -PLATFORM_CLASS_MAPPED_TO_KOTLIN

interface IBase : Map<String, String>

interface TestDerivedInterfaceHiding : IBase {
    fun replace(key: String, value: String): String?
}

interface TestDerivedInterfaceDefault : IBase {
    fun replace(key: String, value: String): String? = TODO()
}

/* GENERATED_FIR_TAGS: functionDeclaration, interfaceDeclaration, nullableType */
