//package com.example.rickmorty.util
//
//
//import android.os.Build
//import androidx.lifecycle.ViewModel
//import kotlinx.coroutines.runBlocking
//import org.junit.Before
//import org.junit.runner.RunWith
//import org.robolectric.RobolectricTestRunner
//import org.robolectric.annotation.Config
//
//@RunWith(RobolectricTestRunner::class)
//@Config(sdk = [Build.VERSION_CODES.P])
//abstract class ViewModelTest<TestViewModel : ViewModel> : CommonTest() {
//
//    private lateinit var viewModel: TestViewModel
//
//    @Before
//    fun setUp() {
//        initCommons()
//
//        runBlocking {
//            viewModel = buildViewModel()
//        }
//    }
//
//    abstract suspend fun buildViewModel(): TestViewModel
//
//    open fun transformViewModel(viewModel: TestViewModel): TestViewModel {
//        return viewModel
//    }
//
//    fun safeTest(action: TestViewModel.() -> Unit) {
//        action(transformViewModel(viewModel))
//    }
//
//    fun safeCoroutineTest(blockingAction: suspend TestViewModel.() -> Unit) {
//        runBlocking {
//            blockingAction(transformViewModel(viewModel))
//        }
//    }
//}