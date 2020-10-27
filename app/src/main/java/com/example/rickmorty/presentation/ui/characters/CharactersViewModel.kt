package com.example.rickmorty.presentation.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.framework.data.getData
import com.example.rickmorty.framework.data.getError
import com.example.rickmorty.framework.data.succeeded
import com.example.rickmorty.interactors.GetCharacters
import com.example.rickmorty.framework.utils.Event
import com.example.rickmorty.framework.utils.LoadingStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel that receives GetCharacters use case
 */
class CharactersViewModel(private val getCharacters: GetCharacters) : ViewModel() {

    private val _characters = MutableLiveData<com.example.rickmorty.domain.CharactersResponse>()
    val characters: LiveData<com.example.rickmorty.domain.CharactersResponse>
        get() = _characters

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    private val _charactersErrorMessage = MutableLiveData<String>()
    val charactersErrorMessage: LiveData<String>
        get() = _charactersErrorMessage

    // para probar los eventos
    private val _charactersResultMessage = MutableLiveData<Event<String>>()
    val charactersResultMessage: LiveData<Event<String>>
        get() = _charactersResultMessage

    init {
        Log.i("CharactersViewModel", "VM was created")
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING

            val charactersResult = getCharacters.invoke()

            if (charactersResult.succeeded) {
                _characters.value = charactersResult.getData()
                _loadingStatus.value = LoadingStatus.SUCCESS
                _charactersResultMessage.value = Event("Data actualizada")
            } else {
                _charactersResultMessage.value =
                    Event(charactersResult.getError().message ?: "Error desconocido")

                _charactersErrorMessage.value = charactersResult.getError().message
                _loadingStatus.value = LoadingStatus.ERROR
            }
        }
    }
}
