package com.example.senakitchnew.ui.Platos

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.R
import com.example.senakitchnew.databinding.ItemPlatosBinding
import com.example.senakitchnew.send.PlatosSend

class PlatosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ItemPlatosBinding = ItemPlatosBinding.bind(view)

    fun render(contentModel: PlatosSend) {
        binding.title.text = contentModel.name
        binding.Preci.text = contentModel.price


        // Verificar si la URL es para un video o una imagen
        if (contentModel.image_path?.matches(Regex(".+\\.(mp4|avi|mov|mkv|wmv|flv|webm)$", RegexOption.IGNORE_CASE)) == true) {
            // Si es un video, usar VideoView
            Log.d("imagenUrl", ApiConnection.baseUrl + contentModel.image_path)
            binding.imageView.visibility = View.GONE
        } else {
            // Si es una imagen, usar Glide para cargar la imagen en el ImageView
            Log.d("ImageUrl", ApiConnection.baseUrl + contentModel.image_path)
            binding.imageView.visibility = View.VISIBLE
            Glide.with(itemView.context)
                .load(ApiConnection.baseUrl + contentModel.image_path)
                .placeholder(R.drawable.logofinal2023)
                .into(binding.imageView)
        }
    }






}



