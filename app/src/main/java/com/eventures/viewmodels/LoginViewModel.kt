package com.eventures.viewmodels

import androidx.lifecycle.ViewModel
import com.eventures.api.AccessTokenRequest
import com.eventures.api.AccessTokenResponse
import com.eventures.models.Admin
import com.eventures.models.Client
import com.eventures.models.LoginUiState
import com.eventures.repository.ApiCallWrapper
import com.eventures.repository.EVenturesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class LoginViewModel: ViewModel() {

    private val eVenturesRepository = EVenturesRepository()

    private var _loginUiStateFlow = MutableStateFlow(
        LoginUiState(
            authorizable = Client(
                id = UUID.randomUUID().toString(),
                name = "Sherley Powers",
                email = "sherlleypowers@gmail.com",
                phone = "796584498",
                address = "Nairobi, Kenya",
                authorities = mutableListOf()
            ),
            authorizationToken = AccessTokenResponse(
                accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjE1MzY0MzI3LTMzY2MtNDRkOC05MmI1LTAzNDk0NTk3Y2E2NCIsImdyYW50X3R5cGUiOiJhZG1pbl9jcmVkZW50aWFscyIsInVzZXJuYW1lIjoiam9obmRvZSIsImVtYWlsIjoiam9obmRvZUBleGFtcGxlLmNvbSIsInJvbGVzIjpbeyJuYW1lIjoiUk9MRV9BRE1JTiIsImRlc2NyaXB0aW9uIjoiU3VwZXIgdXNlciJ9XSwiZ3JvdXBzIjpbeyJuYW1lIjoiR1JPVVBfQURNSU4iLCJkZXNjcmlwdGlvbiI6IlN1cGVyIHVzZXJzIn1dLCJleHAiOjE3MDgxMDkxNDJ9.lh3gILDvbix3L_ixChtfLxElA4F-b-KMZQyR08LF-uqUNG8jdd7hD4qD_dw1WlIGGvA02R865ocmGqKqETA4Tw",
                expires = 4557L,
                realm = "Bearer"
            ),
            status = 0)
    )

    val loginUiStateFlow
        get() = _loginUiStateFlow.asStateFlow()

    fun changeLoginStatus(newStatus: Int) {
        _loginUiStateFlow.value = _loginUiStateFlow.value.copy(status = newStatus)
    }

    suspend fun login(accessTokenRequest: AccessTokenRequest) {
        ApiCallWrapper.call(
            apiCall = { eVenturesRepository.login(accessTokenRequest) },
            onError = { _, _, _, _ -> }
        ).also { tokenResponse ->
            tokenResponse?.let { token ->
                _loginUiStateFlow.value = LoginUiState(
                    authorizable = when(accessTokenRequest.grantType) {
                        "admin_credentials" -> ApiCallWrapper.call(
                            apiCall = { eVenturesRepository.fetchUserInformation<Admin>(
                                authorization = token.accessToken
                            ) },
                            onError = {_, _, _, _ -> }
                        )
                        else -> ApiCallWrapper.call(
                            apiCall = { eVenturesRepository.fetchUserInformation<Client>(
                                authorization = token.accessToken
                            ) },
                            onError = {_, _, _, _ -> }
                        )
                    },
                    status = 1,
                    authorizationToken = token
                )
            }
        }
    }
}