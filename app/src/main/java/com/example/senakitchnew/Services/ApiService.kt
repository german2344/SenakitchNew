package com.example.senakitchnew.Services

import com.example.senakitchnew.UsuariosResponse
import com.example.senakitchnew.bring.RegistroBring
import com.example.senakitchnew.bring.LoginBring
import com.example.senakitchnew.bring.ProductBring
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
import retrofit2.Response
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


    ///////
    @GET("/api/product/{id}")
    fun getMyProduct(@Path("id") id:String): Call<List<ProductSend>>



    //////
    @GET("/api/product/{id}")
    fun getOneContent(@Path("id") id: String): Call<ProductSend>

    @DELETE("/api/users/{userId}")
    fun deleteUser(@Path("userId") userId: String): Call<Void>

    @DELETE("/api/menu/{userId}")
    fun deletePlatos(@Path("menu") userId: String): Call<Void>

    @PUT("api/users/{userId}")
    fun updateProfile(@Body userRequest: UserBring, @Path("userId") userId: String): Call<User>

    @Multipart
    @POST("/api/product")
    fun createContent(
        @Part("name") name: RequestBody,
        @Part("price") price: RequestBody?,
        @Part("description") description: RequestBody,
        @Part("quantity") quantity: RequestBody,
        @Part Url: MultipartBody.Part,
        @Part("user_id") userId: RequestBody
    ): Call<ProductSend>

    @DELETE("/api/product/{productId}")
    fun deleteProduct(@Path("productId") productId: String): Call<Void>



    @POST("/api/product")
    fun createProduct(@Body productRequest: ProductBring): Call<ProductBring>


    @DELETE("/api/product/{id}")
    fun deleteContent(@Path("id") id: String): Call<Void>


    @PUT("api/product/{productId}")
    fun updateProduct(@Body productRequest: ProductBring, @Path("productId") productId: String): Call<Void>

}
