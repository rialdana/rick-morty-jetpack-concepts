package com.example.rickmorty.interactors

import com.example.rickmorty.framework.data.CharactersRepository

class GetCharacterDetail(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterId: Int) =
        charactersRepository.getCharacterDetail(characterId)
}