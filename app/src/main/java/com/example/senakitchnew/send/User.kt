package com.example.senakitchnew.send

data class User (
    val id: Int,
    val name: String,
    val email: String,
    val description: String?,
    val email_verified_at: String?,
    val external_id: String?,
    val external_auth: String?,
    val profile_photo_url: String?,
    val two_factor_confirmed_at: String?
)