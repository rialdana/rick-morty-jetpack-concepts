package com.example.rickmorty.data

import com.example.rickmorty.data.models.characters.CharactersResponse

interface RickMortyRepository {
    suspend fun getCharacters(): CharactersResponse
}