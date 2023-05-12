package ru.vagavagus.gamerules.domain.use_case.sport_list

import android.content.SharedPreferences
import ru.vagavagus.gamerules.common.Constants
import ru.vagavagus.gamerules.data.model.Language

class ChangeLanguage(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): String {
        val lang = sharedPreferences.getString(Constants.LANGUAGE_KEY, Language.EN.value) ?: Language.EN.value

        when(lang) {
            Language.EN.value -> {
                writeLanguage(Language.RU.value)
                return Language.RU.value
            }
            Language.RU.value -> {
                writeLanguage(Language.EN.value)
                return Language.EN.value
            }
        }

        return lang
    }

    private fun writeLanguage(lang: String) {
        sharedPreferences
            .edit()
            .putString(Constants.LANGUAGE_KEY, lang)
            .apply()
    }
}