package com.eventures.viewmodels

import androidx.lifecycle.ViewModel
import com.eventures.api.AccessTokenRequest
import com.eventures.models.Admin
import com.eventures.models.Client
import com.eventures.models.LoginUiState
import com.eventures.repository.ApiCallWrapper
import com.eventures.repository.EVenturesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {

    private val eVenturesRepository = EVenturesRepository()

    private var _loginUiStateFlow = MutableStateFlow<LoginUiState>(
        LoginUiState(null, null, 0)
    )

    val loginUiStateFlow
        get() = _loginUiStateFlow.asStateFlow()

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
                    authorizationToken = token
                )
            }
        }
    }
}