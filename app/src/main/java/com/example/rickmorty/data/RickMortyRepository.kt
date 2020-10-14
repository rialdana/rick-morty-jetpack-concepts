package com.example.rickmorty.data

import com.example.rickmorty.data.models.characters.Character
import com.example.rickmorty.data.models.characters.CharactersResponse

interface RickMortyRepository {

    suspend fun getCharacters(): Result<CharactersResponse>

    suspend fun getCharacterDetail(characterId: Int): Result<Character>
}