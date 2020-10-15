package com.example.rickmorty.framework.data

import com.example.rickmorty.domain.Character
import com.example.rickmorty.domain.CharactersResponse

class DefaultRickMortyRepository(private val remoteDataSource: CharactersDataSource) :
    CharactersRepository {

    override suspend fun getCharacters(): Result<CharactersResponse> {
        return remoteDataSource.getCharacters()
    }

    override suspend fun getCharacterDetail(characterId: Int): Result<Character> = remoteDataSource.getCharacterDetail(characterId)

}