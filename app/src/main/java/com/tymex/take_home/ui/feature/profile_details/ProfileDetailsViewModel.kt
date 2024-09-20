package com.tymex.take_home.ui.feature.profile_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.data.model.DataState
import com.data.network.model.UserDTO

abstract class ProfileDetailsViewModel: ViewModel() {
    abstract val infoDetailLiveData: LiveData<DataState<UserDTO>>
    abstract fun getInfoDetailsUser(userName: String)
}