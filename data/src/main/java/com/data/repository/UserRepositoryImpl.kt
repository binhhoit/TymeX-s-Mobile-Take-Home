package com.data.repository

import com.data.common.SharePreferenceManager
import com.data.network.model.User
import com.data.network.remote.ServiceAPI
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.UUID

class UserRepositoryImpl constructor(private var localData : SharePreferenceManager,
                                     private var serviceAPI: ServiceAPI) : UserRepository {

    override suspend fun getListProfileUser(
        perPage: String,
        since: String
    ) = serviceAPI.getListProfileUser(perPage, since) /*= runBlocking<List<User>> {
        val itemType = object : TypeToken<List<User>>() {}.type
        Gson().fromJson(fakeData, itemType)
    }
*/
    override suspend fun getUserInfo(userName: String) = serviceAPI.getUserInfo(userName)


}