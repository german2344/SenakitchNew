package com.example.senakitchnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.databinding.ActivityLoginBinding
import com.example.senakitchnew.databinding.ActivityProductosBinding
import com.example.senakitchnew.send.LoginSend
import com.example.senakitchnew.send.ProductSend
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response


class productos_activity : AppCompatActivity() {

//    lateinit var binding: ActivityProductosBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityProductosBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val retroftiTraer =  ApiConnection.getApiService().getProduct(ProductBring = null)
//        retroftiTraer.enqueue(object : Callback<ProductSend> {
//            override fun onResponse(call: Call<ProductSend>, response: Response<ProductSend>) {
//                binding.tvMostrar.text = response.body().toString()
//            }
//
//            override fun onFailure(call: Call<ProductSend>, t: Throwable) {
//                Toast.makeText(this@productos_activity, "Error al consultar el api rest", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}






