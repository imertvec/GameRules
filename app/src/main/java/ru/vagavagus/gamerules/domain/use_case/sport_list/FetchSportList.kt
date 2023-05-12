package ru.vagavagus.gamerules.domain.use_case.sport_list

import android.util.Log
import kotlinx.coroutines.flow.flow
import okio.IOException
import ru.vagavagus.gamerules.common.RequestResult
import ru.vagavagus.gamerules.domain.repository.SportRepository

class FetchSportList(
    private val repository: SportRepository
) {
    private val tag = this::class.simpleName

    operator fun invoke(language: String) = flow {
        try {
            emit(RequestResult.Loading())
            val data = repository.fetchSportList(language)
            emit(RequestResult.Success(data))
        } catch (e: IOException) {
            Log.e(tag, "invoke: ", e.cause)
            emit(RequestResult.Error(e.localizedMessage ?: "Internet error"))
        } catch (e: Exception) {
            Log.e(tag, "invoke: ", e)
            emit(RequestResult.Error(e.localizedMessage ?: "Unexpected error"))
        }
    }
}