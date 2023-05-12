package ru.vagavagus.gamerules.domain.use_case.sport_list

import android.content.SharedPreferences
import ru.vagavagus.gamerules.common.Constants
import ru.vagavagus.gamerules.data.model.Language

class FetchCurrentLanguage(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): String {
        return sharedPreferences.getString(Constants.LANGUAGE_KEY, Language.EN.value) ?: Language.EN.value
    }
}