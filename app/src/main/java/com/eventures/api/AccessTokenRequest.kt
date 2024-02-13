package com.eventures.api

import com.google.gson.annotations.SerializedName

data class AccessTokenRequest(
    @SerializedName("grant_type") var grantType: String,
    @SerializedName("client_id") var clientId: String,
    @SerializedName("client_secret") var clientSecret: String,
)