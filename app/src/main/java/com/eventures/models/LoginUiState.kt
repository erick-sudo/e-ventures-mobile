package com.eventures.models

import com.eventures.api.AccessTokenResponse

data class LoginUiState(
    var authorizable: Authorizable?,
    var authorizationToken: AccessTokenResponse?,
    var status: Int = 0
)