package com.example.rickmorty.presentation.ui.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.framework.data.getData
import com.example.rickmorty.framework.data.getError
import com.example.rickmorty.framework.data.succeeded
import com.example.rickmorty.interactors.GetCharacterDetail
import com.example.rickmorty.framework.utils.LoadingStatus
import kotlinx.coroutines.launch

/**
 * ViewModel that receives GetCharacterDetail use case
 */
class CharacterDetailViewModel(
    private val getCharacterDetail: GetCharacterDetail,
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

    fun getCharacterDetail() {
        viewModelScope.launch {

            _loadingStatus.value = LoadingStatus.LOADING

            val response = getCharacterDetail.invoke(characterId)

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
