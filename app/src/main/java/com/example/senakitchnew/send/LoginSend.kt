package com.example.senakitchnew.send

data class LoginSend (
    val message: String,
    val access_token: String,
    val token_type: String,
    val user: User
)

