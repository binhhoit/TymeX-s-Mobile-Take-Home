package com.data.domain.user

import com.data.network.model.UserDTO
import com.data.repository.UserRepository
import kotlinx.coroutines.flow.flow

class UsersUseCase(private val userRepository: UserRepository) {
    fun getListProfileUser(perPage: String, since: String) = flow {
        val result = userRepository.getListProfileUser(perPage = perPage, since = since)
        emit(result.map {
            UserDTO(
                login = it.login,
                avatarUrl = it.avatarUrl,
                htmlUrl = it.htmlUrl,
                id = it.id,
            )
        })
    }

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
