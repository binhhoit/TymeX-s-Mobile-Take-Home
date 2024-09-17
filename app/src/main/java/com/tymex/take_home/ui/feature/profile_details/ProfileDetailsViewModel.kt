package com.tymex.take_home.ui.feature.profile_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.data.model.DataState

abstract class ProfileDetailsViewModel: ViewModel() {

    abstract val loginLiveData: LiveData<DataState<Boolean>>
    abstract fun doLogin(userName: String)

}