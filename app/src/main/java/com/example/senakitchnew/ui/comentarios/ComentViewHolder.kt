package com.example.senakitchnew.ui.comentarios

import android.view.View
import androidx.recyclerview.widget.RecyclerView

import com.example.senakitchnew.databinding.ItemComentBinding
import com.example.senakitchnew.send.ComentSend
import com.example.senakitchnew.send.ProductSend

class ComentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ItemComentBinding = ItemComentBinding.bind(view)

    fun render(contentModel: ComentSend) {
        binding.name.text = contentModel.id
        binding.description.text = contentModel.description
        try {
            // Intentar convertir el valor de String a Float
            val ratingValue = contentModel.rating.toFloat()
            binding.ratingBar2.rating = ratingValue
        } catch (e: NumberFormatException) {
            // Manejar la excepción en caso de que la conversión falle
            // Puedes imprimir un mensaje de error o tomar alguna otra acción apropiada.
            e.printStackTrace()
        }
    }

}



