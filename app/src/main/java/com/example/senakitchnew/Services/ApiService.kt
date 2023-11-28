package com.example.senakitchnew.Services

import com.example.senakitchnew.bring.RegistroBring
import com.example.senakitchnew.bring.LoginBring
import com.example.senakitchnew.bring.UserBring
import com.example.senakitchnew.send.ComentSend
import com.example.senakitchnew.send.LoginSend
import com.example.senakitchnew.send.PlatosSend
import com.example.senakitchnew.send.ProductSend
import com.example.senakitchnew.send.RegisterSend
import com.example.senakitchnew.send.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @POST("/api/register")
    fun registrausuario(@Body Registromodel: RegistroBring): Call<RegisterSend>

    @POST("/api/login")
    fun loginUser(@Body loginRequest: LoginBring): Call<LoginSend>

    @GET("/api/users/{userId}")
    fun getUserProfile(@Path("userId") userId: String): Call<User>

    @GET("/api/product")
    fun getProduct(): Call<List<ProductSend>>

    @GET("/api/menu")
    fun getMenu(): Call<List<PlatosSend>>

    @GET("/api/comment")
    fun getComment(): Call<List<ComentSend>>

    @DELETE("/api/users/{userId}")
    fun deleteUser(@Path("userId") userId: String): Call<Void>

//    @DELETE("/api/product/{productId}")
//    fun deleteProduct(@Path("productId") userId: String): Call<Void>
    @DELETE("/api/menu/{userId}")
    fun deletePlatos(@Path("menu") userId: String): Call<Void>

    @PUT("api/users/{userId}")
    fun updateProfile(@Body userRequest: UserBring, @Path("userId") userId: String): Call<User>

    @GET("/api/recipe")
    fun getRecetas(): Call<List<ProductSend>>

    @DELETE("/api/product/{productId}")
    fun deleteProduct(@Path("productId") productId: String): Call<Void>


    @POST("/api/product/{productId}")
    fun addProduct(@Body newProduct: ProductSend): Call<Void>


    @GET("api/product/{id}")
    fun getMyContent(@Path("id") id:String): Call<List<ProductSend>>

    @Multipart
    @POST("/api/product")
    fun createContent(
        @Part("name") title: RequestBody,
        @Part("price") price: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("autor") autor: RequestBody,
        @Part("description") description: RequestBody,
        @Part("user_id") userId: RequestBody
    ): Call<ProductSend>

}