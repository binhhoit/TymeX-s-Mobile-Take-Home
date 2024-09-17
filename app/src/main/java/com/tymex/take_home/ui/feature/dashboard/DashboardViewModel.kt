package com.tymex.take_home.ui.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.data.model.DataState

abstract class DashboardViewModel: ViewModel() {

    abstract val loginLiveData: LiveData<DataState<Boolean>>
    abstract fun doLogin(userName: String)

}