package ru.vagavagus.gamerules.domain.repository

import ru.vagavagus.gamerules.domain.model.SportDetails
import ru.vagavagus.gamerules.domain.model.SportItem

interface SportRepository {
    suspend fun fetchSportList(language: String): List<SportItem>
    suspend fun fetchSportDetailsByReference(language: String, reference: String): SportDetails
}