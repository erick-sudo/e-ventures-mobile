package com.eventures.models

import com.eventures.api.AccessTokenResponse

interface Authorizable {
    var id: String
    var name: String
    var email: String
    var phone: String
    var authorities: MutableList<String>
}