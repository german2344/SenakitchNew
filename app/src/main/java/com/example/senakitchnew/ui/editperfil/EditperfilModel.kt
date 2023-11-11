package com.example.senakitchnew.ui.editperfil

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.send.User
import com.example.senakitchnew.send.UserAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditperfilModel: ViewModel() {

    init {
        val userId = UserAdmin.getUserId()
        Log.e("IDUSER", "${userId}")
        getUserProfile(userId.toString())
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    private val userById = MutableLiveData<User>()
    val user: LiveData<User> get() = userById


    private fun getUserProfile(userId: String) {
        val apiService = ApiConnection.getApiService()

        val userProfileCall: Call<User> = apiService.getUserProfile(userId)
        userProfileCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        userById.value = it
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Error user", t.toString())
            }
        })
    }

}