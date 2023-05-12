package ru.vagavagus.gamerules.presentation.screens.sport_details

import ru.vagavagus.gamerules.domain.model.SportDetails

sealed class SportDetailsState {
    object Loading: SportDetailsState()
    class Error(val message: String): SportDetailsState()
    class Success(val data: SportDetails): SportDetailsState()
}
