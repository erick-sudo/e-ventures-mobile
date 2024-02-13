package com.eventures.models

import com.google.gson.annotations.SerializedName

data class Client(
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("phone") var phone: String,
    @SerializedName("address") var address: String
)
