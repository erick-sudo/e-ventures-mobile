package com.eventures.models

import com.google.gson.annotations.SerializedName

class Client(
    @SerializedName("id") override  var id: String,
    @SerializedName("name") override var name: String,
    @SerializedName("email") override var email: String,
    @SerializedName("phone") override var phone: String,
    @SerializedName("address") var address: String,
    @SerializedName("authorities") override var authorities: MutableList<String>,
): Authorizable
