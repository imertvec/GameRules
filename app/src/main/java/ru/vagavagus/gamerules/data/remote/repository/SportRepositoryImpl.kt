package ru.vagavagus.gamerules.data.remote.repository

import ru.vagavagus.gamerules.data.remote.Api
import ru.vagavagus.gamerules.data.remote.SportDetailsDto
import ru.vagavagus.gamerules.data.remote.SportItemDto
import ru.vagavagus.gamerules.domain.model.SportDetails
import ru.vagavagus.gamerules.domain.model.SportItem
import ru.vagavagus.gamerules.domain.repository.SportRepository

class SportRepositoryImpl(
    private val api: Api
): SportRepository {

    override suspend fun fetchSportList(language: String): List<SportItem> {
        return api.fetchSportList(language).map { it.toSportItem() }
    }

    override suspend fun fetchSportDetailsByReference(
        language: String,
        reference: String
    ): SportDetails {
        return api.fetchSportDetailsByReference(language, reference).toSportDetails()
    }

    //MARK: fun-converters
    private fun SportItemDto.toSportItem() = SportItem(name, dataReference)
    private fun SportDetailsDto.toSportDetails() = SportDetails(name, img, desc)
}