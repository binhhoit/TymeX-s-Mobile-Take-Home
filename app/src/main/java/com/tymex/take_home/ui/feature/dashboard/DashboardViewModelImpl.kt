package com.tymex.take_home.ui.feature.dashboard

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

class DashboardViewModelImpl(private val usersUseCase: UsersUseCase): DashboardViewModel() {

    private val listProfileUsers = mutableListOf<UserDTO>()
    private val _usersLiveData = MutableLiveData<DataState<List<UserDTO>>>()
    override val usersLiveData get() = _usersLiveData

    /**
     * The function create flow observer status request data list users
     * Can check status loading, success, catching error
     * @param perPage limit on page
     * @param since number page request
     * */
    override fun getProfileUsers(perPage:String, since:String) {
        usersUseCase.getListProfileUser(perPage, since)
            .flowOn(Dispatchers.IO)
            .onStart {
                _usersLiveData.value = DataState.Loading(true)
            }
            .onEach {
                listProfileUsers.addAll(it)
                _usersLiveData.value = DataState.Success(listProfileUsers.distinctBy { user -> user.login })
            }
            .catch {
                _usersLiveData.value = DataState.Failure(it)
            }
            .onCompletion {
                _usersLiveData.value = DataState.Loading(false)
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }


}