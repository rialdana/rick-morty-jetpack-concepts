package com.example.rickmorty.presentation.ui.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickmorty.domain.CharactersResponse
import com.example.rickmorty.framework.data.CharactersRepository
import com.example.rickmorty.framework.data.Result
import com.example.rickmorty.framework.utils.LoadingStatus
import com.example.rickmorty.interactors.GetCharacters
import com.example.rickmorty.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    private lateinit var charactersViewModel: CharactersViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getCharacters_returnError() = runBlockingTest {

        val charactersRepository = mock(CharactersRepository::class.java)

        `when`(charactersRepository.getCharacters()).thenReturn(
            Result.Error(Exception("Unable to get characters"))
        )

        charactersViewModel = CharactersViewModel(GetCharacters(charactersRepository))

        charactersViewModel.getCharacters()

        val value = charactersViewModel.charactersErrorMessage.getOrAwaitValue()

        assertThat(value, (not(nullValue())))
    }

    @Test
    fun getCharacters_returnSuccess() = runBlockingTest {

        val charactersResponse = CharactersResponse(null, emptyList())

        val charactersRepository = mock(CharactersRepository::class.java)

        `when`(charactersRepository.getCharacters()).thenReturn(
            Result.Success(CharactersResponse(null, emptyList()))
        )

        charactersViewModel = CharactersViewModel(GetCharacters(charactersRepository))

        charactersViewModel.getCharacters()

        val value = charactersViewModel.characters.getOrAwaitValue()
        val loadingStatus = charactersViewModel.loadingStatus.getOrAwaitValue()

        assertThat(value, `is`(charactersResponse))
        assertThat(loadingStatus, `is`(LoadingStatus.SUCCESS))
    }
}