package com.example.senakitchnew.bring

data class ProductBring (
     val name : String,
    val price : String,
    val image : String,
    val description: String,
     val slug: String?,
     val url: String,
     val autor: String,
     val user_id: Int
    )