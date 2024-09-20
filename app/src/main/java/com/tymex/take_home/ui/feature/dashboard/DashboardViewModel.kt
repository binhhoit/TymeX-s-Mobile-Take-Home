package com.tymex.take_home.ui.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.data.model.DataState
import com.data.network.model.UserDTO

abstract class DashboardViewModel: ViewModel() {

    abstract val usersLiveData: LiveData<DataState<List<UserDTO>>>
    abstract fun getProfileUsers(perPage: String, since: String)

}