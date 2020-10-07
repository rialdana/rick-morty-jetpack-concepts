package com.example.rickmorty.data.network

import com.example.rickmorty.data.Result
import com.example.rickmorty.data.models.characters.CharactersResponse

class RemoteRickMortyDataSource(private val apiService: RickMortyApiService) : RickMortyDataSource {

    override suspend fun getCharacters(): Result<CharactersResponse> {
        return try {
            val charactersResponse = apiService.getCharacters()
            Result.Success(charactersResponse)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}