
package com.tymex.take_home.ui.feature.ui.main

import com.tymex.take_home.ui.feature.main.MainViewModel
import com.tymex.take_home.ui.feature.main.MainViewModelImpl
import com.tymex.take_home.viewmodel.BaseViewModelTest
import org.junit.Before
import org.mockito.MockitoAnnotations

class MainViewModelTest : BaseViewModelTest<MainViewModel>() {

    override fun createViewModel(): MainViewModel {
        return MainViewModelImpl()
    }

    @Before
    override fun setUp() {
        MockitoAnnotations.openMocks(this)
        super.setUp()
    }

}