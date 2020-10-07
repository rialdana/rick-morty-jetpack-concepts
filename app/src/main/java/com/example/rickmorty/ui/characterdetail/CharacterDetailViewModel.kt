package com.example.rickmorty.ui.characterdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rickmorty.data.RickMortyRepository

class CharacterDetailViewModel(
    private val repository: RickMortyRepository,
    private val characterId: Int
) :
    ViewModel() {

    fun sayHi() {
        Log.i("CharacterDetailViewModel", "Character ID: $characterId")
    }
}