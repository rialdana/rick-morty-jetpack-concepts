package com.example.rickmorty.data.network

import com.example.rickmorty.data.models.characters.CharactersResponse

interface RickMortyDataSource {
    suspend fun getCharacters(): CharactersResponse
}