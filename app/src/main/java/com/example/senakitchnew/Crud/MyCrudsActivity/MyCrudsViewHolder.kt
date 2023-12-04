package com.example.senakitchnew.Crud.MyCrudsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.R
import com.example.senakitchnew.databinding.MyItemContentBinding
import com.example.senakitchnew.send.ProductSend

class MyCrudsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: MyItemContentBinding = MyItemContentBinding.bind(view)


    fun render(item: ProductSend, onClickListener: (ProductSend) -> Unit) {
        binding.name.text = item.name
        binding.price.text = item.price
        binding.description.text = item.description
        binding.quantity.text = item.quantity
        binding.price.text = item.price
        // Verficar si la URL es para un video o una imagen
        if (item.url?.matches(Regex(".+\\.(mp4|avi|mov|mkv|wmv|flv|webm)$", RegexOption.IGNORE_CASE)) == true) {
            // Si es un video, usar VideoView
            Log.d("imagenUrl", ApiConnection.baseUrl + item.url)
            binding.imageView.visibility = View.GONE
        } else {
            // Si es una imagen, usar Glide para cargar la imagen en el ImageView
            Log.d("ImageUrl", ApiConnection.baseUrl + item.url)
            binding.imageView.visibility = View.VISIBLE
            Glide.with(itemView.context)
                .load(ApiConnection.baseUrl + item.url)
                .placeholder(R.drawable.logofinal2023)
                .into(binding.imageView)


        }

    }
}
