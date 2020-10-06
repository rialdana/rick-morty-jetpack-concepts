package com.example.rickmorty.di

import com.example.rickmorty.data.DefaultRickMortyRepository
import com.example.rickmorty.data.RickMortyRepository
import com.example.rickmorty.data.network.RemoteRickMortyDataSource
import com.example.rickmorty.data.network.RickMortyApiService
import com.example.rickmorty.data.network.RickMortyDataSource
import org.koin.dsl.module

val appModule = module {

    fun createRepository(remoteDataSource: RickMortyDataSource): RickMortyRepository {
        return DefaultRickMortyRepository(remoteDataSource)
    }

    fun createDataSource(apiService: RickMortyApiService): RickMortyDataSource {
        return RemoteRickMortyDataSource(apiService)
    }

    // Singleton para el data source
    single { createDataSource(get()) }

    // Singleton para el repository
    single { createRepository(get()) }
}