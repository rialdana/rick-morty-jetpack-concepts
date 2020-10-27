package com.example.rickmorty.framework.data

import com.example.rickmorty.domain.Character
import com.example.rickmorty.domain.CharactersResponse

interface CharactersDataSource {
    suspend fun getCharacters(): Result<CharactersResponse>

    suspend fun getCharacterDetail(characterId: Int): Result<Character>
}
