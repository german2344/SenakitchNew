package com.example.senakitchnew.ui.Crud

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.send.ProductSend
import com.example.senakitchnew.send.User
import com.example.senakitchnew.send.UserAdmin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {

//    init {
//        getAllContent()
//    }

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text

    private val _contentData = MutableLiveData<List<ProductSend>>()
    val contentData: LiveData<List<ProductSend>> get() = _contentData

    private val userById = MutableLiveData<User>()
    val user: LiveData<User> get() = userById
//    private fun getAllContent() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val apiGetContent = ApiConnection.getApiService().getProduct()
//
//            apiGetContent.enqueue(object: Callback<List<ProductSend>> { // Cambiado a List<ContentResponse>
//                override fun onResponse(
//                    call: Call<List<ProductSend>>,
//                    response: Response<List<ProductSend>>
//                ) {
//                    if (response.isSuccessful) {
//                        val contentResponseList = response.body()
//                        contentResponseList?.let {
//                            _contentData.value = it
//
//                            Log.d("ListViewModel", "Datos recibidos: $it")
//                        }
//                    }
//                }
//                override fun onFailure(call: Call<List<ProductSend>>, t: Throwable) {
//                    Log.e("Error content", t.toString())
//                }
//            })
//        }
//    }
}