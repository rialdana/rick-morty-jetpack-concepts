package com.example.rickmorty.framework.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickmorty.domain.Character
import com.example.rickmorty.domain.CharactersResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class DefaultRickMortyRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockDataSource: CharactersDataSource
    private lateinit var repository: CharactersRepository

    @Before
    fun setupDataSource() {
        mockDataSource = Mockito.mock(CharactersDataSource::class.java)
        repository = DefaultRickMortyRepository(mockDataSource)
    }

    @Test
    fun getCharacters_returnError() = runBlockingTest {
        val message = "Unable to get info"

        `when`(mockDataSource.getCharacters()).thenReturn(Result.Error(Exception(message)))

        val result = repository.getCharacters()

        result as Result.Error

        assertThat(result.exception.message, `is`(message))
    }

    @Test
    fun getCharacters_returnSuccess() = runBlockingTest {

        val character = CharactersResponse(null, emptyList())

        `when`(mockDataSource.getCharacters()).thenReturn(Result.Success(character))

        val result = repository.getCharacters()

        result as Result.Success

        assertThat(result.data, `is`(character))
    }

    @Test
    fun getCharacterDetail_returnError() = runBlockingTest {

        val message = "Unable to get character"
        `when`(mockDataSource.getCharacterDetail(1)).thenReturn(Result.Error(Exception(message)))

        val result = repository.getCharacterDetail(1)
        result as Result.Error

        assertThat(result.exception.message, `is`(message))
    }

    @Test
    fun getCharacterDetail_returnSuccess() = runBlockingTest {
        val character =
            Character(1, null, null, null, null, null, null, null, null, null, null, null)

        `when`(mockDataSource.getCharacterDetail(1)).thenReturn(Result.Success(character))

        val result = repository.getCharacterDetail(1)
        result as Result.Success

        assertThat(result.data.id, `is`(character.id))
    }
}