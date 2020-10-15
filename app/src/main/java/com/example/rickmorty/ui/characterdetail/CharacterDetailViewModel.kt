package com.example.rickmorty.ui.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.CharactersRepository
import com.example.rickmorty.data.getData
import com.example.rickmorty.data.getError
import com.example.rickmorty.data.succeeded
import com.example.rickmorty.utils.LoadingStatus
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val repository: CharactersRepository,
    private val characterId: Int
) : ViewModel() {

    private val _character = MutableLiveData<com.example.rickmorty.domain.Character>()
    val character: LiveData<com.example.rickmorty.domain.Character>
        get() = _character

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    private val _characterErrorMessage = MutableLiveData<String>()
    val characterErrorMessage: LiveData<String>
        get() = _characterErrorMessage

    init {
        getCharacterDetail()
    }

    private fun getCharacterDetail() {
        viewModelScope.launch {

            _loadingStatus.value = LoadingStatus.LOADING

            val response = repository.getCharacterDetail(characterId)
            if (response.succeeded) {
                _character.value = response.getData()
                _loadingStatus.value = LoadingStatus.SUCCESS
            } else {
                _characterErrorMessage.value = response.getError().message
                _loadingStatus.value = LoadingStatus.ERROR
            }
        }
    }
}