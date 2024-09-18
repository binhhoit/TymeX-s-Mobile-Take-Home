package com.tymex.take_home.ui.feature.profile_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.data.domain.user.UsersUseCase
import com.data.model.DataState
import com.data.network.model.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class ProfileDetailsViewModelImpl(private val usersUseCase: UsersUseCase): ProfileDetailsViewModel() {
    private val _infoDetailLiveData = MutableLiveData<DataState<UserDTO>>()
    override val infoDetailLiveData get() = _infoDetailLiveData

    override fun getInfoDetailsUser(userName: String) {
        usersUseCase.getProfileUsers(userName)
            .flowOn(Dispatchers.IO)
            .onStart {
                _infoDetailLiveData.value = DataState.Loading(true)
            }
            .onEach {
                _infoDetailLiveData.value = DataState.Success(it)
            }
            .catch {
                _infoDetailLiveData.value = DataState.Failure(it)
            }
            .onCompletion {
                _infoDetailLiveData.value = DataState.Loading(false)
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }


}