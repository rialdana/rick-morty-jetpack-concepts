package com.example.rickmorty.framework.di

import com.example.rickmorty.BuildConfig
import com.example.rickmorty.framework.data.network.RickMortyApiService
import com.example.rickmorty.framework.utils.BASE_URL
import com.example.rickmorty.framework.utils.CONNECT_TIMEOUT
import com.example.rickmorty.framework.utils.READ_TIMEOUT
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    fun createRetrofit(moshi: Moshi, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(httpClient)
            .build()
    }

    fun createOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val okhttp = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(logging)
                }
            }

        return okhttp.build()
    }

    fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun createMoshi(): Moshi? {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single { createMoshi() }
    single { createLoggingInterceptor() }
    single { createOkHttpClient(get()) }
    single { createRetrofit(get(), get()) }
    single { get<Retrofit>().create(RickMortyApiService::class.java) }
}
