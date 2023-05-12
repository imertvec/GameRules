package ru.vagavagus.gamerules.presentation.screens.sport_list

sealed class SportListEvent {
    class FetchList(val language: String): SportListEvent()
    object ChangeLanguage: SportListEvent()
}
