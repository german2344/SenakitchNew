package com.example.senakitchnew

import com.example.senakitchnew.send.ProductSend
import com.google.gson.annotations.SerializedName

data class UsuariosResponse(
    @SerializedName("listaUsuarios") var listaProduct: ArrayList<ProductSend>
)
