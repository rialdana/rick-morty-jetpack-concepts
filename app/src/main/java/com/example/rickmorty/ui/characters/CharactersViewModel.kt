package com.example.rickmorty.ui.characters

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.RickMortyRepository
import com.example.rickmorty.data.models.characters.CharactersResponse
import com.example.rickmorty.utils.Event
import com.example.rickmorty.utils.LoadingStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(private val repository: RickMortyRepository) : ViewModel() {

    private val _characters = MutableLiveData<CharactersResponse>()
    val characters: LiveData<CharactersResponse>
        get() = _characters

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

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

            _characters.value = repository.getCharacters()
            _loadingStatus.value = LoadingStatus.SUCCESS
            _charactersResultMessage.value = Event("Data actualizada")


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