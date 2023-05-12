package ru.vagavagus.gamerules.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportDetailsDto (
    @Json(name = "name") val name: String,
    @Json(name = "img") val img: String,
    @Json(name = "desc") val desc: String
)