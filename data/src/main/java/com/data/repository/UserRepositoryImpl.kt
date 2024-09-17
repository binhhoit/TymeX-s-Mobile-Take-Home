package com.data.repository

import com.data.common.SharePreferenceManager
import com.data.network.remote.ServiceAPI
import java.util.UUID

class UserRepositoryImpl constructor(private var localData : SharePreferenceManager,
                                     private var serviceAPI: ServiceAPI) : UserRepository {

    override suspend fun getListProfileUser(perPage: String, since: String) = serviceAPI.getListProfileUser(perPage, since)

    override suspend fun getUserInfo(): String {
        return localData.userNameInfo
    }


}
