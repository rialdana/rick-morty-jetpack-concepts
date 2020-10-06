package com.example.rickmorty.data.models.characters


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponse(
    val info: Info?,
    val results: List<Result>?
)