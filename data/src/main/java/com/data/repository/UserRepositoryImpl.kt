package com.data.repository

import com.data.common.SharePreferenceManager
import com.data.network.model.User
import com.data.network.remote.ServiceAPI

class UserRepositoryImpl constructor(private var localData : SharePreferenceManager,
                                     private var serviceAPI: ServiceAPI) : UserRepository {

    private var firstLoadUsers = true

    /**
    * The function can load new data when first open app or second request.
    * If local have data will priority load fist.
    * @param perPage limit on page
    * @param since number page request
    * */
    override suspend fun getListProfileUser(
        perPage: String,
        since: String
    ): List<User> {
        val usersLocal = localData.users
        return if (firstLoadUsers && !usersLocal.isNullOrEmpty()) {
            firstLoadUsers = false
            usersLocal
        } else {
            firstLoadUsers = false
            serviceAPI.getListProfileUser(perPage, since)
        }
    }

    /**
    * function load details info user
    * @param userName the name address for request info user
    * */
    override suspend fun getUserInfo(userName: String) = serviceAPI.getUserInfo(userName)


}