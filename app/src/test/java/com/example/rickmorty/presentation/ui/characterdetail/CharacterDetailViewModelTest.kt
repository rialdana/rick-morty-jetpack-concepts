package com.example.rickmorty.presentation.ui.characterdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickmorty.domain.Character
import com.example.rickmorty.framework.data.CharactersRepository
import com.example.rickmorty.framework.data.Result
import com.example.rickmorty.framework.utils.LoadingStatus
import com.example.rickmorty.interactors.GetCharacterDetail
import com.example.rickmorty.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repositoryMock: CharactersRepository
    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    @Before
    fun setupViewModel() {
        repositoryMock = mock(CharactersRepository::class.java)
        characterDetailViewModel = CharacterDetailViewModel(GetCharacterDetail(repositoryMock), 1)
    }

    @Test
    fun getCharacterDetail_returnError() = runBlockingTest {

        `when`(repositoryMock.getCharacterDetail(1)).thenReturn(Result.Error(Exception("Unable to get them")))

        characterDetailViewModel.getCharacterDetail()

        val value = characterDetailViewModel.characterErrorMessage.getOrAwaitValue()
        val loadingStatus = characterDetailViewModel.loadingStatus.getOrAwaitValue()

        assertThat(value, not(nullValue()))
        assertThat(loadingStatus, `is`(LoadingStatus.ERROR))
    }

    @Test
    fun getCharacterDetail_returnSuccess() = runBlockingTest {
        `when`(repositoryMock.getCharacterDetail(1)).thenReturn(
            Result.Success(
                Character(
                    1,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
        )

        characterDetailViewModel.getCharacterDetail()

        val value = characterDetailViewModel.character.getOrAwaitValue()
        val loadingStatus = characterDetailViewModel.loadingStatus.getOrAwaitValue()

        assertThat(value.id, `is`(1))
        assertThat(loadingStatus, `is`(LoadingStatus.SUCCESS))
    }
}