package com.eventures.api

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_in") val expires: Long,
    @SerializedName("realm") val realm: String
)