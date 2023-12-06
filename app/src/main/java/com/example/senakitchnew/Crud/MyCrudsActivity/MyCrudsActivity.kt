package com.example.senakitchnew.Crud.MyCrudsActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.Crud.CrudUpdateActivity
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.R
import com.example.senakitchnew.send.ProductSend
import com.example.senakitchnew.send.User
import com.example.senakitchnew.send.UserAdmin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCrudsActivity : AppCompatActivity() {


//    private lateinit var binding: ActivityMyCrudsBinding
//    private val toast = popupalert()
//
//    var userData: User? = null
//    private var userId = 0
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMyCrudsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        userId = UserAdmin.getUserId()
//
//      getUserProfile(userId.toString())
//        initRecyclerView()
//    }
//
//    private fun initRecyclerView()
//    {
//        binding.recyclerMyContent.layoutManager = LinearLayoutManager(this)
//        getAllContent()
//    }
//
//    private fun getAllContent() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val apiGetContent = ApiConnection.getApiService().getProduct()
//
//            apiGetContent.enqueue(object :
//                Callback<List<ProductSend>> { // Cambiado a List<ContentResponse>
//                override fun onResponse(
//                    call: Call<List<ProductSend>>,
//                    response: Response<List<ProductSend>>
//                ) {
//                    if (response.isSuccessful) {
//                        val contentResponseList = response.body()
//                        contentResponseList?.let {
//                            val myContent = MyCrudsAdapter(contentResponseList) { myContent ->
//                                onItemSelected(
//                                    myContent
//                                )
//                            }
//                            binding.recyclerMyContent.adapter = myContent
//                        }
//                    }
//                }
//                override fun onFailure(call: Call<List<ProductSend>>, t: Throwable) {
//                    toast.toastError(
//                        this@MyCrudsActivity,
//                        "Error 😢😢😢😢😢",
//                        "Ups!, ha ocurrido un error"
//                    )
//                    Log.e("Error content", t.toString())
//                }
//            })
//        }
//    }
//    fun onItemSelected(myContentResponse: ProductSend) {
//        Log.d("MyCrudsActivity", "Selected content ID: ${myContentResponse.id}")
//        val intent = Intent(this, CrudUpdateActivity::class.java)
//        intent.putExtra("CONTENIDO_ID", myContentResponse.id)
//        startActivity(intent)
//    }
//
//
//    private fun getUserProfile(userId: String) {
//        val apiService = ApiConnection.getApiService()
//
//        val userProfileCall: Call<User> = apiService.getUserProfile(userId)
//        userProfileCall.enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if (response.isSuccessful) {
//                    userData = response.body()
//                    userData?.let {}
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                toast.toastError(this@MyCrudsActivity, "Conexión", "Error de conexión")
//            }
//        })
//    }

}