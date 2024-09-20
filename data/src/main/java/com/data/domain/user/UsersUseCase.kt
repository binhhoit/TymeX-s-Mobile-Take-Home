package com.data.domain.user

import com.data.common.SharePreferenceManager
import com.data.network.model.UserDTO
import com.data.repository.UserRepository
import kotlinx.coroutines.flow.flow

/**
 * Class UsersUseCase have summary the feature user and adapter prepare data for viewmodel
 * */
class UsersUseCase(private val userRepository: UserRepository,
                   private var localData: SharePreferenceManager) {

    /**
     * The function request list users into Github and convert list data User -> list UserDTO
     * @param perPage limit on page
     * @param since number page request
     * */
    fun getListProfileUser(perPage: String, since: String) = flow {
        val result = userRepository.getListProfileUser(perPage = perPage, since = since)
        localData.users = result

        emit(result.map {
            UserDTO(
                login = it.login,
                avatarUrl = it.avatarUrl,
                htmlUrl = it.htmlUrl,
                id = it.id,
            )
        })
    }

    /**
     * function load details info user and convert list User -> UserDTO
     * @param userName the name address for request info user
     * */
    fun getProfileUsers(userName: String) = flow {
        val result = userRepository.getUserInfo(userName)
        emit(UserDTO(
            login = result.login,
            avatarUrl = result.avatarUrl,
            htmlUrl = result.htmlUrl,
            id = result.id,
            followers = result.followers,
            location = result.location,
            following = result.following,
        ))
    }
}
