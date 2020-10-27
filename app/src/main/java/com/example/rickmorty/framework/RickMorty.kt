package com.example.rickmorty.framework

import android.app.Application
import com.example.rickmorty.framework.di.appModule
import com.example.rickmorty.framework.di.retrofitModule
import com.example.rickmorty.framework.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Open class to inject dependencies
 */
open class RickMorty : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RickMorty)
            modules(modules())
        }
    }

    open fun modules() = listOf(appModule, retrofitModule, viewModelModule)
}
