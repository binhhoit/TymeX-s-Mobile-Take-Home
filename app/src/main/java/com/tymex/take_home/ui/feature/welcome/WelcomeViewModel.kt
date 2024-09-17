package com.tymex.take_home.ui.feature.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class WelcomeViewModel: ViewModel() {

    abstract val isLogin: LiveData<Boolean>

}