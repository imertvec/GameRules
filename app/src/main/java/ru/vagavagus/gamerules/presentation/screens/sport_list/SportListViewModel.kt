package ru.vagavagus.gamerules.presentation.screens.sport_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vagavagus.gamerules.common.RequestResult
import ru.vagavagus.gamerules.domain.use_case.sport_list.ChangeLanguage
import ru.vagavagus.gamerules.domain.use_case.sport_list.FetchCurrentLanguage
import ru.vagavagus.gamerules.domain.use_case.sport_list.FetchSportList
import javax.inject.Inject

@HiltViewModel
class SportListViewModel @Inject constructor(
    private val fetchSportListUseCase: FetchSportList,
    private val changeLanguageUseCase: ChangeLanguage,
    fetchCurrentLanguageUseCase: FetchCurrentLanguage
): ViewModel() {

    private val _state: MutableStateFlow<SportListState> = MutableStateFlow(SportListState.Loading())
    val state: StateFlow<SportListState> = _state

    init {
        val lang = fetchCurrentLanguageUseCase()
        handleEvent(event = SportListEvent.FetchList(lang))
    }

    fun handleEvent(event: SportListEvent) {
        when(event) {
            is SportListEvent.FetchList -> fetchList(event.language)
            is SportListEvent.ChangeLanguage -> {
                val newLang = changeLanguageUseCase()
                fetchList(newLang)
            }
        }
    }

    private fun fetchList(language: String) = viewModelScope.launch {
        fetchSportListUseCase(language).collect { result ->
            when(result) {
                is RequestResult.Error -> _state.update { SportListState.Error(result.message!!, language) }
                is RequestResult.Loading -> _state.update { SportListState.Loading(language) }
                is RequestResult.Success -> _state.update { SportListState.Success(result.data!!, language) }
            }
        }
    }
}