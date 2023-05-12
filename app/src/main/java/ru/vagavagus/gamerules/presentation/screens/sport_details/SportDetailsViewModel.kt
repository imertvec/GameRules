package ru.vagavagus.gamerules.presentation.screens.sport_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vagavagus.gamerules.common.RequestResult
import ru.vagavagus.gamerules.domain.use_case.sport_details.FetchSportDetails
import ru.vagavagus.gamerules.domain.use_case.sport_list.FetchCurrentLanguage
import javax.inject.Inject

@HiltViewModel
class SportDetailsViewModel @Inject constructor(
    private val fetchSportDetailsUseCase: FetchSportDetails,
    private val fetchCurrentLanguage: FetchCurrentLanguage,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val reference = savedStateHandle.get<String>("dataReference") ?: throw IllegalArgumentException("Illegal dataReference arg")

    private val _state: MutableStateFlow<SportDetailsState> = MutableStateFlow(SportDetailsState.Loading)
    val state: StateFlow<SportDetailsState> = _state

    init {
        viewModelScope.launch {
            fetchSportDetailsUseCase(fetchCurrentLanguage(), reference).collect { result ->
                when(result) {
                    is RequestResult.Loading -> _state.update { SportDetailsState.Loading }
                    is RequestResult.Error -> _state.update { SportDetailsState.Error(result.message ?: "Something went wrong") }
                    is RequestResult.Success -> result.data?.let { _state.update { SportDetailsState.Success(data = result.data) } }
                }
            }
        }
    }
}