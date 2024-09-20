package com.data.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDTO(
    @SerializedName("login"               ) var login             : String?  = null,
    @SerializedName("avatar_url"          ) var avatarUrl         : String?  = null,
    @SerializedName("html_url"            ) var htmlUrl           : String?  = null,
    @SerializedName("id"                  ) var id                : Int?     = null,
    @SerializedName("followers"           ) var followers         : Int?  = null,
    @SerializedName("following"           ) var following         : Int?  = null,
    @SerializedName("location"            ) var location          : String?  = null,
) : Serializable