package com.eventures.api

import com.google.gson.annotations.SerializedName

data class RegistrationConfirmationRequest(
    @SerializedName("identity") var phoneOrEmail: String,
    @SerializedName("initial_password") var initialPassword: String,
    @SerializedName("new_password") var newPassword: String,
    @SerializedName("confirm_password") var confirmPassword: String
)
