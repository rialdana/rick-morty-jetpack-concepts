package com.example.rickmorty.data

import com.example.rickmorty.data.models.characters.Character
import com.example.rickmorty.data.models.characters.CharactersResponse
import com.example.rickmorty.data.network.RickMortyDataSource

class DefaultRickMortyRepository(private val remoteDataSource: RickMortyDataSource) :
    RickMortyRepository {

    override suspend fun getCharacters(): Result<CharactersResponse> {
        return remoteDataSource.getCharacters()
    }

    override suspend fun getCharacterDetail(characterId: Int): Result<Character> = remoteDataSource.getCharacterDetail(characterId)

}