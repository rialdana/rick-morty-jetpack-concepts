//package com.example.rickmorty.util
//
//import androidx.test.core.app.ApplicationProvider
//import com.example.rickmorty.framework.TestRickMortyApplication
//import org.koin.dsl.ModuleDeclaration
//import org.koin.dsl.module
//import org.koin.test.AutoCloseKoinTest
//import org.mockito.Mockito
//import org.mockito.invocation.InvocationOnMock
//import org.mockito.stubbing.OngoingStubbing
//import org.mockito.stubbing.Stubber
//
//abstract class CommonTest : AutoCloseKoinTest() {
//
//    lateinit var context: TestRickMortyApplication
//
//    fun initCommons() {
//        context = ApplicationProvider.getApplicationContext()
//    }
//
//    fun injectModule(moduleDeclaration: ModuleDeclaration) {
//        context.injectModule(module(moduleDeclaration = moduleDeclaration))
//    }
//
//    inline fun <reified Class> mock(): Class {
//        return Mockito.mock(Class::class.java)
//    }
//
//    fun <T> on(methodCall: T): OngoingStubbing<T> {
//        return Mockito.`when`(methodCall)
//    }
//
//    fun doAnswer(action: (invocationOnMock: InvocationOnMock) -> Unit): Stubber {
//        return Mockito.doAnswer {
//            action(it)
//        }
//    }
//
//    fun doNothing(): Stubber {
//        return Mockito.doNothing()
//    }
//
//    fun <T> spy(thing: T): T {
//        return Mockito.spy(thing)
//    }
//
//    inline fun <reified Class> spy(): Class {
//        return Mockito.spy(Class::class.java)
//    }
//
//    inline fun <reified Class> any(): Class = Mockito.any(Class::class.java)
//}