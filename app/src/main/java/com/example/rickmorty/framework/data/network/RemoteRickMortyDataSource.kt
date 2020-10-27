package com.example.rickmorty.framework.data.network

import com.example.rickmorty.framework.data.Result
import com.example.rickmorty.framework.data.CharactersDataSource
import com.example.rickmorty.domain.Character
import com.example.rickmorty.domain.CharactersResponse
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Remote data source to fetch characters from api
 */
class RemoteRickMortyDataSource(private val apiService: RickMortyApiService) :
    CharactersDataSource {

    override suspend fun getCharacters(): Result<CharactersResponse> {
        return try {
            val charactersResponse = apiService.getCharacters()
            Result.Success(charactersResponse)
        } catch (e: KotlinNullPointerException) {
            Result.Error(e)
        } catch (e: UnknownHostException) {
            Result.Error(e)
        } catch (e: JsonDataException) {
            Result.Error(e)
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

    override suspend fun getCharacterDetail(characterId: Int): Result<Character> {
        return try {
            val characterDetailResponse = apiService.getCharacterDetail(characterId)
            Result.Success(characterDetailResponse)
        } catch (e: KotlinNullPointerException) {
            Result.Error(e)
        } catch (e: UnknownHostException) {
            Result.Error(e)
        } catch (e: JsonDataException) {
            Result.Error(e)
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }
}
