package ru.vagavagus.gamerules.presentation.screens.sport_list

import ru.vagavagus.gamerules.data.model.Language
import ru.vagavagus.gamerules.domain.model.SportItem

sealed class SportListState(var language: String) {
    class Loading(language: String = Language.EN.value): SportListState(language)
    class Success(val data: List<SportItem>, language: String): SportListState(language)
    class Error(val message: String, language: String): SportListState(language)
}
