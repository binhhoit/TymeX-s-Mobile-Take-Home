package com.tymex.take_home.ui.feature.profile_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.data.domain.user.LoginUserCase
import com.data.model.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class ProfileDetailsViewModelImpl(private val loginUserCase: LoginUserCase): ProfileDetailsViewModel() {

    private val _loginLiveData = MutableLiveData<DataState<Boolean>>()
    override val loginLiveData get() = _loginLiveData

    override fun doLogin(userName: String) {
        loginUserCase.execute(userName)
            .flowOn(Dispatchers.IO)
            .onStart {
                _loginLiveData.value = DataState.Loading(true)
            }
            .onEach {
                _loginLiveData.value = DataState.Success(it)
            }
            .catch {
                _loginLiveData.value = DataState.Failure(it)
            }
            .onCompletion {
                _loginLiveData.value = DataState.Loading(false)
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

}