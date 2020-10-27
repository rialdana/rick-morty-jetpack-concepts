package com.example.rickmorty.framework.di

import com.example.rickmorty.framework.data.DefaultRickMortyRepository
import com.example.rickmorty.framework.data.CharactersRepository
import com.example.rickmorty.framework.data.network.RemoteRickMortyDataSource
import com.example.rickmorty.framework.data.network.RickMortyApiService
import com.example.rickmorty.framework.data.CharactersDataSource
import com.example.rickmorty.interactors.GetCharacterDetail
import com.example.rickmorty.interactors.GetCharacters
import org.koin.dsl.module

val appModule = module {

    fun createRepository(remoteDataSource: CharactersDataSource): CharactersRepository {
        return DefaultRickMortyRepository(remoteDataSource)
    }

    fun createDataSource(apiService: RickMortyApiService): CharactersDataSource {
        return RemoteRickMortyDataSource(apiService)
    }

    // Singleton para el data source
    single { createDataSource(get()) }

    // Singleton para el repository
    single { createRepository(get()) }

    single { GetCharacters(get()) }

    single { GetCharacterDetail(get()) }
}
