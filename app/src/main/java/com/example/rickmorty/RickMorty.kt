package com.example.rickmorty

import android.app.Application
import com.example.rickmorty.di.appModule
import com.example.rickmorty.di.retrofitModule
import com.example.rickmorty.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RickMorty : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RickMorty)
            modules(listOf(appModule, retrofitModule, viewModelModule))
        }
    }
}