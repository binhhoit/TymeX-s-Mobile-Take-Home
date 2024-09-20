
package com.tymex.take_home.ui.feature.ui.welcome

import com.tymex.take_home.ui.feature.welcome.WelcomeViewModel
import com.tymex.take_home.ui.feature.welcome.WelcomeViewModelImpl
import com.tymex.take_home.viewmodel.BaseViewModelTest
import org.junit.Before
import org.mockito.MockitoAnnotations

class WelcomeViewModelTest : BaseViewModelTest<WelcomeViewModel>() {

    override fun createViewModel(): WelcomeViewModel {
        return WelcomeViewModelImpl()
    }

    @Before
    override fun setUp() {
        MockitoAnnotations.openMocks(this)
        super.setUp()
    }
}