package com.example.rickmorty.data.network

import com.example.rickmorty.data.models.characters.CharactersResponse

class RemoteRickMortyDataSource(private val apiService: RickMortyApiService) : RickMortyDataSource {

    override suspend fun getCharacters(): CharactersResponse {
        return apiService.getCharacters()
    }
}