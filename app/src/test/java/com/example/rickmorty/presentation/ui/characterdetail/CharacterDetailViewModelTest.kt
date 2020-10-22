package com.example.rickmorty.presentation.ui.characterdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickmorty.domain.Character
import com.example.rickmorty.framework.data.CharactersRepository
import com.example.rickmorty.framework.data.FakeRickMortyRepositoryTest
import com.example.rickmorty.framework.data.Result
import com.example.rickmorty.framework.utils.LoadingStatus
import com.example.rickmorty.interactors.GetCharacterDetail
import com.example.rickmorty.util.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterDetailViewModelTest {

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getCharacterDetail_returnError() = runBlockingTest {

        val myMock = mock(CharactersRepository::class.java)

        `when`(myMock.getCharacterDetail(1)).thenReturn(Result.Error(Exception("Unable to get them")))

        characterDetailViewModel =
            CharacterDetailViewModel(GetCharacterDetail(myMock), 1)

        characterDetailViewModel.getCharacterDetail()

        val value = characterDetailViewModel.characterErrorMessage.getOrAwaitValue()
        val loadingStatus = characterDetailViewModel.loadingStatus.getOrAwaitValue()

        assertThat(value, not(nullValue()))
        assertThat(loadingStatus, `is`(LoadingStatus.ERROR))
    }

    @Test
    fun getCharacterDetail_returnSuccess() = runBlockingTest {

        val myMock = mock(CharactersRepository::class.java)

        `when`(myMock.getCharacterDetail(1)).thenReturn(
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

        characterDetailViewModel =
            CharacterDetailViewModel(GetCharacterDetail(myMock), 1)

        characterDetailViewModel.getCharacterDetail()

        val value = characterDetailViewModel.character.getOrAwaitValue()
        val loadingStatus = characterDetailViewModel.loadingStatus.getOrAwaitValue()

        assertThat(value.id, `is`(1))
        assertThat(loadingStatus, `is`(LoadingStatus.SUCCESS))
    }
}