package com.tymex.take_home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import com.tymex.take_home.rule.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseViewModelTest<V : ViewModel> {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = TestCoroutineRule()

    lateinit var mViewModel: V

    @Before
    open fun setUp() {
        mViewModel = createViewModel()
    }

    abstract fun createViewModel(): V
}