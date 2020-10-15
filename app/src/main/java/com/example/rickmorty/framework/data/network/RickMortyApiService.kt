package com.example.rickmorty.framework.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RickMortyApiService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("character")
    suspend fun getCharacters(): com.example.rickmorty.domain.CharactersResponse

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("character/{character_id}")
    suspend fun getCharacterDetail(@Path("character_id") characterId: Int): com.example.rickmorty.domain.Character
}