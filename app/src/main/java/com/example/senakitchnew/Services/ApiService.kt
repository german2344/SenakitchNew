package com.example.senakitchnew.Services

import com.example.senakitchnew.bring.RegistroBring
import com.example.senakitchnew.bring.LoginBring
import com.example.senakitchnew.bring.ProductBring
import com.example.senakitchnew.send.LoginSend
import com.example.senakitchnew.send.ProductSend
import com.example.senakitchnew.send.RegisterSend
import com.example.senakitchnew.send.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/api/register")
    fun registrausuario(@Body Registromodel: RegistroBring): Call<RegisterSend>


    @POST("/api/login")
    fun loginUser(@Body loginRequest: LoginBring): Call<LoginSend>

    @GET("/api/users/{userId}")
    fun getUserProfile(@Path("userId") userId: String): Call<User>
    @GET("/api/logout/")
    fun logoutUser(): Call<RegisterSend>?

    @GET("/api/product")
    fun getProduct(): Call<List<ProductSend>>
}