package com.example.rickmorty.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.CharactersRepository
import com.example.rickmorty.data.getData
import com.example.rickmorty.data.getError
import com.example.rickmorty.data.succeeded
import com.example.rickmorty.interactors.GetCharacters
import com.example.rickmorty.utils.Event
import com.example.rickmorty.utils.LoadingStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private fun getCharacters() {
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

            val message: String = sleepThread()
            Log.i("CharactersViewModel", message)

            sleepThread2()

            // _characters.value = CharactersResponse(null, emptyList())
        }
    }

    private suspend fun sleepThread() = withContext(Dispatchers.IO) {

        Thread.sleep(3000)

        return@withContext "Hi there, this is the returned value"
    }

    private suspend fun sleepThread2() {
        withContext(Dispatchers.IO) {
            Thread.sleep(3000)
        }
    }
}