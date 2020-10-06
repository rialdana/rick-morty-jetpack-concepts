package com.example.rickmorty.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.RickMortyRepository
import com.example.rickmorty.data.models.characters.CharactersResponse
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: RickMortyRepository) : ViewModel() {

    private val _characters = MutableLiveData<CharactersResponse>()
    val characters: LiveData<CharactersResponse>
        get() = _characters

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            _characters.value = repository.getCharacters()
        }
    }
}