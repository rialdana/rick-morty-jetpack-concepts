package com.example.rickmorty.data.network

import com.example.rickmorty.data.Result
import com.example.rickmorty.data.models.characters.Character
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

    override suspend fun getCharacterDetail(characterId: Int): Result<Character> {
        return try {
            val characterDetailResponse = apiService.getCharacterDetail(characterId)
            Result.Success(characterDetailResponse)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}