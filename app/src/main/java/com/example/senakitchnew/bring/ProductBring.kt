package com.example.senakitchnew.bring

import okhttp3.RequestBody

data class ProductBring(
    val name: String,
    val price: String, // Assuming this is a String for now
    val image: RequestBody?,
    val description: String,
    var quantity: String?,
    val user_id: Int
)


