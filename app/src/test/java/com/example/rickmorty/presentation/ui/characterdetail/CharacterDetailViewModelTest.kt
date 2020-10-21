package com.example.rickmorty.presentation.ui.characterdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickmorty.domain.Character
import com.example.rickmorty.framework.data.CharactersRepository
import com.example.rickmorty.framework.data.FakeRickMortyRepositoryTest
import com.example.rickmorty.framework.utils.LoadingStatus
import com.example.rickmorty.interactors.GetCharacterDetail
import com.example.rickmorty.util.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test

class CharacterDetailViewModelTest {
    private lateinit var characterDetailViewModel: CharacterDetailViewModel
    private lateinit var charactersRepository: CharactersRepository


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getCharacterDetail_returnError() {
        charactersRepository =
            FakeRickMortyRepositoryTest(null, null)

        characterDetailViewModel =
            CharacterDetailViewModel(GetCharacterDetail(charactersRepository), 1)

        characterDetailViewModel.getCharacterDetail()

        val value = characterDetailViewModel.characterErrorMessage.getOrAwaitValue()
        val loadingStatus = characterDetailViewModel.loadingStatus.getOrAwaitValue()

        assertThat(value, not(nullValue()))
        assertThat(loadingStatus, CoreMatchers.`is`(LoadingStatus.ERROR))
    }

    @Test
    fun getCharacterDetail_returnSuccess() {
        charactersRepository =
            FakeRickMortyRepositoryTest(
                null,
                Character(1, "Rick", null, null, null, null, null, null, null, null, null, null)
            )

        characterDetailViewModel =
            CharacterDetailViewModel(GetCharacterDetail(charactersRepository), 1)

        characterDetailViewModel.getCharacterDetail()

        val value = characterDetailViewModel.character.getOrAwaitValue()
        val loadingStatus = characterDetailViewModel.loadingStatus.getOrAwaitValue()

        assertThat(value.id, `is`(1))
        assertThat(loadingStatus, CoreMatchers.`is`(LoadingStatus.SUCCESS))
    }
}