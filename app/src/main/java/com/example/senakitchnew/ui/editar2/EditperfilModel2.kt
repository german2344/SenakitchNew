package com.example.senakitchnew.ui.editar2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EditperfilModel2 {
    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}