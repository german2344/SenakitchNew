package com.example.senakitchnew.send

data class ProductSend (
    var id : String,
    var name : String,
    var price : String,
    var description: String,
    val quantity:String,
    val url:String,
    val user_id: String
    )