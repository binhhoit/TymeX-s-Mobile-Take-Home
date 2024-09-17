package com.data.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDTO(
    @SerializedName("login"               ) var login             : String?  = null,
    @SerializedName("avatar_url"          ) var avatarUrl         : String?  = null,
    @SerializedName("html_url"            ) var htmlUrl           : String?  = null,
    @SerializedName("id"                  ) var id                : Int?     = null,
) : Serializable
