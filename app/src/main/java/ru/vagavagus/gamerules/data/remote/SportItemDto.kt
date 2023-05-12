package ru.vagavagus.gamerules.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportItemDto(
    @Json(name = "name") val name: String,
    @Json(name = "data_reference") val dataReference: String
)
