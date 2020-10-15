package com.example.rickmorty.data

import com.example.rickmorty.domain.Character
import com.example.rickmorty.domain.CharactersResponse

interface CharactersRepository {

    suspend fun getCharacters(): Result<CharactersResponse>

    suspend fun getCharacterDetail(characterId: Int): Result<Character>
}