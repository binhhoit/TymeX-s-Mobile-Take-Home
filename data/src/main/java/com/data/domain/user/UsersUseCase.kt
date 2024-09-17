package com.data.domain.user

import com.data.network.model.UserDTO
import com.data.repository.UserRepository
import kotlinx.coroutines.flow.flow

class UsersUseCase(private val userRepository: UserRepository) {
    fun getListProfileUser(perPage: String, since: String) = flow {
        val result = userRepository.getListProfileUser(perPage = perPage, since = since)
        emit(result.map { UserDTO(login = it.login, avatarUrl = it.avatarUrl, htmlUrl = it.htmlUrl, id = it.id) })
    }
}
