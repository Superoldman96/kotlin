// RUN_PIPELINE_TILL: BACKEND
annotation class Ann(vararg val strings: String)

@Ann(strings = ["hello"])
class A
