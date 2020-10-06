package com.example.rickmorty.data.models.characters


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    val count: Int?,
    val pages: Int?,
    val next: String?,
    val prev: Any?
)