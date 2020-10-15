package com.example.rickmorty.framework.data.network

import com.example.rickmorty.framework.data.Result
import com.example.rickmorty.framework.data.CharactersDataSource
import com.example.rickmorty.domain.Character
import com.example.rickmorty.domain.CharactersResponse

class RemoteRickMortyDataSource(private val apiService: RickMortyApiService) :
    CharactersDataSource {

    override suspend fun getCharacters(): Result<CharactersResponse> {
        return try {
            val charactersResponse = apiService.getCharacters()
            Result.Success(charactersResponse)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getCharacterDetail(characterId: Int): Result<Character> {
        return try {
            val characterDetailResponse = apiService.getCharacterDetail(characterId)
            Result.Success(characterDetailResponse)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}