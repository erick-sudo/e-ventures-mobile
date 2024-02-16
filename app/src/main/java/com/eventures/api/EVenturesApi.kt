package com.eventures.api

import com.eventures.models.Client
import com.eventures.models.Loan
import com.eventures.models.LoanRepayment
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface EVenturesApi {

    @POST("entryventures/api/v1/auth/access-token")
    suspend fun login(@Body loginCredentials: AccessTokenRequest): AccessTokenResponse

    /**
     * An admin creates a client with provided details
     * An initial password gets sent to the client's email or phone as SMS
     */
    @POST("CS/clients")
    suspend fun clientRegistration(@Body clientRegistration: Client): ClientRegistrationResponse

    /**
     * Client confirms registration with initial password and changes password
     */
    @POST("entryventures/api/v1/auth/clients/registration/confirmation")
    suspend fun clientRegistrationConfirmation(@Body clientRegistrationConfirmationRequest: RegistrationConfirmationRequest): RegistrationConfirmationResponse

    /**
     * Obtain user information
     */
    @GET("entryventures/api/v1/auth/user-info")
    suspend fun <U> fetchUserInformation(@Header("Authorization") authorization: String): U

    @GET("loans/{loanId}")
    suspend fun fetchLoan(@Header("Authorization") authorization: String, @Path("loanId") loanId: String): Loan

    @GET("loans/pagination/{pageNumber}/{pageSize}")
    suspend fun fetchLoans(@Header("Authorization") authorization: String, @Path("pageNumber") pageNumber: Int, @Path("pageSize") pageSize: Int): List<Loan>

    @GET("loans/{loanId}/collections/{pageNumber}/{pageSize}")
    suspend fun fetchLoansRepayments(
        @Header("Authorization") authorization: String,
        @Path("loanId") loanId: String,
        @Path("pageNumber") pageNumber: Int,
        @Path("pageSize") pageSize: Int
    ): List<LoanRepayment>
}