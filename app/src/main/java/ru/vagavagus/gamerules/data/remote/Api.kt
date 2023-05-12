package ru.vagavagus.gamerules.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/7/data/{language}/sport_list.json")
    suspend fun fetchSportList(
        @Path(value = "language") language: String
    ): List<SportItemDto>

    @GET("/7/data/{language}/{reference}.json")
    suspend fun fetchSportDetailsByReference(
        @Path(value = "language") language: String,
        @Path(value = "reference") reference: String
    ): SportDetailsDto

}