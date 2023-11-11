package com.example.senakitchnew.Connection


import com.example.senakitchnew.Services.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConnection {
    val baseUrl = "http://192.168.101.79:8000"

    private fun getRetrofit(): Retrofit {

        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(baseUrl)
            .build()
    }
    fun getApiService(): ApiService{
        return getRetrofit().create(ApiService::class.java)
    }

}

