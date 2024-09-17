package com.tymex.take_home.ui.feature.welcome

import androidx.lifecycle.MutableLiveData
import com.data.common.SharePreferenceManager

class WelcomeViewModelImpl(localData: SharePreferenceManager) : WelcomeViewModel() {
    private val _isLogin= MutableLiveData<Boolean>()
    override val isLogin get() = _isLogin

    init {
        _isLogin.value = localData.userToken.isNotEmpty() && localData.userToken.isNotEmpty()
    }

}