package com.data.network.remote

import com.data.network.model.User
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {
    @GET("/users")
    suspend fun getListProfileUser(
        @Query("per_page") perPage: String,
        @Query("since") since: String,
    ): List<User>
}
