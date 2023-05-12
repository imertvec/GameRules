package ru.vagavagus.gamerules.data.model

enum class Language(val value: String) {
    RU(value = "ru"),
    EN(value = "en")
}

fun String.nextLang(): String {
    if(this == Language.EN.value) return Language.RU.value
    return Language.EN.value
}