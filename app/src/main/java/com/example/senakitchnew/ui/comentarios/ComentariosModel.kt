package com.example.senakitchnew.ui.comentarios

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.send.ComentSend

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComentariosModel: ViewModel() {
    init {
        getAllContent()
    }

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text

    private val _contentData = MutableLiveData<List<ComentSend>>()
    val contentData: LiveData<List<ComentSend>> get() = _contentData

    private fun getAllContent() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiGetContent = ApiConnection.getApiService().getComment()

            apiGetContent.enqueue(object : Callback<List<ComentSend>> { // Cambiado a List<ContentResponse>
                override fun onResponse(
                    call: Call<List<ComentSend>>,
                    response: Response<List<ComentSend>>
                ) {
                    if (response.isSuccessful) {
                        val contentResponseList = response.body()
                        contentResponseList?.let {
                            _contentData.value = it
                        }
                    }
                }

                override fun onFailure(call: Call<List<ComentSend>>, t: Throwable) {
                    Log.e("Error content", t.toString())
                }
            })
        }
    }
}