package com.example.rickmorty.data.network

import com.example.rickmorty.data.models.characters.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface RickMortyApiService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("character")
    suspend fun getCharacters(): CharactersResponse
}