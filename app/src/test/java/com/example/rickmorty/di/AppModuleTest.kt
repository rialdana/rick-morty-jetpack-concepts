//package com.example.rickmorty.di
//
//import android.os.Build
//import androidx.test.core.app.ApplicationProvider
//import com.example.rickmorty.framework.di.appModule
//import com.example.rickmorty.framework.di.retrofitModule
//import com.example.rickmorty.framework.di.viewModelModule
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.koin.android.ext.koin.androidContext
//import org.koin.core.context.GlobalContext
//import org.koin.test.AutoCloseKoinTest
//import org.koin.test.check.checkModules
//import org.robolectric.RobolectricTestRunner
//import org.robolectric.annotation.Config
//
//@RunWith(RobolectricTestRunner::class)
//@Config(sdk = [Build.VERSION_CODES.P])
//class AppModuleTest : AutoCloseKoinTest() {
//
//    private val koinApplication = GlobalContext.get()
//
//    @Before
//    fun before() {
//        // Close koinApplication to be able to set context
//        koinApplication.close()
//        GlobalContext.get().androidContext(ApplicationProvider.getApplicationContext())
//    }
//
//    @Test
//    fun tesKoinComponents() {
//        GlobalContext.get().modules(
//            listOf(
//                appModule, retrofitModule
//            )
//        )
//        GlobalContext.get().checkModules()
//    }
//}