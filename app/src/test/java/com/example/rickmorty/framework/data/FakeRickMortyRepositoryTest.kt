package com.example.rickmorty.framework.data

import com.example.rickmorty.domain.Character
import com.example.rickmorty.domain.CharactersResponse

class FakeRickMortyRepositoryTest(
    private val charactersResponse: CharactersResponse?,
    private val characterDetail: Character?
) : CharactersRepository {
    override suspend fun getCharacters(): Result<CharactersResponse> {
        return if (charactersResponse == null) {
            Result.Error(Exception("Unable to get characters"))
        } else {
            Result.Success(charactersResponse)
        }
    }

    override suspend fun getCharacterDetail(characterId: Int): Result<Character> {
        return if (characterDetail == null) {
            Result.Error(Exception("Unable to get character detail"))
        } else {
            Result.Success(characterDetail)
        }
    }
}