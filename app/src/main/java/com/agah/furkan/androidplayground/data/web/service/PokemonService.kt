package com.agah.furkan.androidplayground.data.web.service

import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.response.PokemonDetailResponse
import com.agah.furkan.androidplayground.data.web.model.response.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offSet: Int,
        @Query("limit") limit: Int
    ): ApiResponse<PokemonResponse>

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetail(
        @Path("pokemonName") pokemonName: String
    ): ApiResponse<PokemonDetailResponse>
}