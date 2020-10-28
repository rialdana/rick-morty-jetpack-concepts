package com.example.rickmorty.framework.data.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickmorty.domain.Character
import com.example.rickmorty.domain.CharactersResponse
import com.example.rickmorty.framework.data.Result
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class RemoteRickMortyDataSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockApiService: RickMortyApiService
    private lateinit var remoteRickMortyDataSource: RemoteRickMortyDataSource

    @Before
    fun setupApiService() {
        mockApiService = Mockito.mock(RickMortyApiService::class.java)
        remoteRickMortyDataSource = RemoteRickMortyDataSource(mockApiService)
    }

    @Test
    fun getCharacters_throwError() = runBlockingTest {

        val message = "Unable to fetch the characters"

        `when`(mockApiService.getCharacters()).thenThrow(JsonDataException(message))

        val result = remoteRickMortyDataSource.getCharacters()

        result as Result.Error

        assertThat(result.exception.message, `is`(message))
    }

    @Test
    fun getCharacters_returnResponse() = runBlockingTest {
        `when`(mockApiService.getCharacters()).thenReturn(CharactersResponse(null, emptyList()))

        val result = remoteRickMortyDataSource.getCharacters()

        result as Result.Success

        assertThat(result.data.results, `is`(emptyList()))
    }

    @Test
    fun getCharacterDetail_throwError() = runBlockingTest {
        val message = "Unable to load character detail"
        `when`(mockApiService.getCharacterDetail(ArgumentMatchers.anyInt())).thenThrow(
            JsonDataException(message)
        )

        val result = remoteRickMortyDataSource.getCharacterDetail(ArgumentMatchers.anyInt())

        result as Result.Error

        assertThat(result.exception.message, `is`(message))
    }

    @Test
    fun getCharacterDetail_returnResponse() = runBlockingTest {
        `when`(mockApiService.getCharacterDetail(ArgumentMatchers.anyInt())).thenReturn(
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

        val result = remoteRickMortyDataSource.getCharacterDetail(ArgumentMatchers.anyInt())

        result as Result.Success

        assertThat(result.data.id, `is`(1))
    }
}