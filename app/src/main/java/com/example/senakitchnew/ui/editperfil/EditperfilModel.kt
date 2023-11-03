package com.example.senakitchnew.ui.editperfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EditperfilModel {
    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}