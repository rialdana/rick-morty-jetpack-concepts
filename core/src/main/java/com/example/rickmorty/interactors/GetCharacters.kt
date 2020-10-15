package com.example.rickmorty.interactors

import com.example.rickmorty.data.CharactersRepository

class GetCharacters(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke() = charactersRepository.getCharacters()
}