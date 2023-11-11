package com.example.senakitchnew.ui.home

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.R
import com.example.senakitchnew.databinding.ItemContentBinding
import com.example.senakitchnew.send.ProductSend

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ItemContentBinding = ItemContentBinding.bind(view)

    fun render(contentModel: ProductSend) {
        binding.title.text = contentModel.name
        binding.author.text = contentModel.price
        binding.description.text = contentModel.description

        // Verificar si la URL es para un video o una imagen
        if (contentModel.image.matches(Regex(".+\\.(mp4|avi|mov|mkv|wmv|flv|webm)$", RegexOption.IGNORE_CASE))) {
            // Si es un video, usar VideoView
            val videoUri = Uri.parse(ApiConnection.baseUrl + contentModel.url)

            binding.imageView.visibility = View.GONE


        } else {
            // Si es una imagen, usar Glide para cargar la imagen en el ImageView
            binding.imageView.visibility = View.VISIBLE
            Glide.with(itemView.context)
                .load(ApiConnection.baseUrl + contentModel.url)
                .placeholder(R.drawable.logo)

                .into(binding.imageView)
        }

    }
}
