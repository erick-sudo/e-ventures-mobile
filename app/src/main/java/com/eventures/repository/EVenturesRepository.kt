package com.eventures.repository

import com.eventures.api.AccessTokenRequest
import com.eventures.api.AccessTokenResponse
import com.eventures.api.ClientRegistrationResponse
import com.eventures.api.EVenturesApi
import com.eventures.api.RegistrationConfirmationRequest
import com.eventures.api.RegistrationConfirmationResponse
import com.eventures.models.Client
import com.eventures.models.Loan
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EVenturesRepository {

    suspend fun login(
        loginCredentials: AccessTokenRequest
    ): AccessTokenResponse = API_INSTANCE
        .login(loginCredentials)

    suspend fun clientRegistration(
        clientRegistrationPayload: Client
    ): ClientRegistrationResponse = API_INSTANCE
        .clientRegistration(clientRegistrationPayload)

    suspend fun clientRegistrationConfirmation(
        clientRegistrationConfirmationRequest: RegistrationConfirmationRequest
    ): RegistrationConfirmationResponse = API_INSTANCE
        .clientRegistrationConfirmation(clientRegistrationConfirmationRequest)

    suspend fun fetchLoan(
        authorization: String,
        loanId: String
    ): Loan = API_INSTANCE.fetchLoan(
        authorization = authorization,
        loanId = loanId
    )

    suspend fun fetchLoans(
        authorization: String,
        pageNumber: Int,
        pageSize: Int
    ): List<Loan> = API_INSTANCE.fetchLoans(
        authorization = authorization,
        pageNumber = pageNumber,
        pageSize = pageSize
    )

    companion object {

        private val API_INSTANCE: EVenturesApi by lazy {

            // Http Client
            val okHttpClient: OkHttpClient = OkHttpClient
                .Builder()
                .build()

            // Retrofit builder
            val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8080/")
                .client(okHttpClient)
                .build()

            // Initialize API
            retrofit.create(EVenturesApi::class.java)
        }
    }
}