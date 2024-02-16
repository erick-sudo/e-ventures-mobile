package com.eventures.models

import com.google.gson.annotations.SerializedName

class Admin (
    @SerializedName("id") override  var id: String,
    @SerializedName("name") override var name: String,
    @SerializedName("email") override var email: String,
    @SerializedName("phone") override var phone: String,
    @SerializedName("authorities") override var authorities: MutableList<String>,
    @SerializedName("user_name") var userName: String,
    @SerializedName("position") var position: String
): Authorizable