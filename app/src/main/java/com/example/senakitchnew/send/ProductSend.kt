package com.example.senakitchnew.send

data class ProductSend (
    val id: Int,
    val name: String?,
    val price: String?,
    val description: String?,
    val quantity: String,
    val created_at:String,
    val updated_at:String,
)
