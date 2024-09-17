package com.data.repository

import com.data.network.model.User

interface UserRepository {
    suspend fun getListProfileUser(perPage: String, since: String): List<User>
    suspend fun getUserInfo():String
}
