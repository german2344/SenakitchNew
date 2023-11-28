package com.example.senakitchnew.ui.Platos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.send.PlatosSend
import com.example.senakitchnew.send.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlatosViewModel : ViewModel() {

    init {
        getAllContent()
    }

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text

    private val _contentData = MutableLiveData<List<PlatosSend>>()
    val contentData: LiveData<List<PlatosSend>> get() = _contentData

    private val userById = MutableLiveData<User>()
    val user: LiveData<User> get() = userById
    private fun getAllContent() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiGetContent = ApiConnection.getApiService().getMenu()

            apiGetContent.enqueue(object : Callback<List<PlatosSend>> { // Cambiado a List<ContentResponse>
                override fun onResponse(
                    call: Call<List<PlatosSend>>,
                    response: Response<List<PlatosSend>>
                ) {
                    if (response.isSuccessful) {
                        val contentResponseList = response.body()
                        contentResponseList?.let {
                            _contentData.value = it
                        }
                    }
                }

                override fun onFailure(call: Call<List<PlatosSend>>, t: Throwable) {
                    Log.e("Error content", t.toString())
                }
            })
        }
    }

    private val _deleteUserResult = MutableLiveData<Boolean>()
    val deleteUserResult: LiveData<Boolean> get() = _deleteUserResult

    fun deleteUser(platosId: String) {
        val apiService = ApiConnection.getApiService()

        val deleteUserCall: Call<Void> = apiService.deletePlatos(platosId)
        deleteUserCall.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Eliminación exitosa
                    Log.d("User deletion", "User deleted successfully")
                    _deleteUserResult.value = true
                } else {
                    // Manejar errores en la respuesta, si es necesario
                    Log.e("User deletion", "Failed to delete user. Response code: ${response.code()}")
                    _deleteUserResult.value = false
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Manejar errores en la solicitud, si es necesario
                Log.e("User deletion", "Error deleting user", t)
                _deleteUserResult.value = false
            }
        })
    }




}
