package com.example.rickmorty.domain

data class CharactersResponse(
    val info: Info?,
    val results: List<com.example.rickmorty.domain.Character>?
)
