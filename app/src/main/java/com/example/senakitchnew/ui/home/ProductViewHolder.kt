package com.example.senakitchnew.ui.home

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.Crud.CrudUpdateActivity
import com.example.senakitchnew.Crud.MyCrudsActivity.CrudUpdate1Activity
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.R
import com.example.senakitchnew.activity_login
import com.example.senakitchnew.databinding.ActivityContentUpdateBinding
import com.example.senakitchnew.databinding.ActivityCrudUpdate1Binding
import com.example.senakitchnew.databinding.ItemContentBinding
import com.example.senakitchnew.send.ProductSend
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    private val binding: ItemContentBinding = ItemContentBinding.bind(view)

    private val toast = popupalert()
    private val TAG = "MyActivity"
    private var idabc = "0"



    fun render(contentModel: ProductSend) {
        idabc = contentModel.id
        binding.title.text = contentModel.name
        binding.Preci.text = contentModel.price
        binding.description.text = contentModel.description
        binding.quantity.text = contentModel.quantity

        // Verificar si la URL es para un video o una imagen
        if (contentModel.url?.matches(Regex(".+\\.(mp4|avi|mov|mkv|wmv|flv|webm)$", RegexOption.IGNORE_CASE)) == true) {
            // Si es un video, usar VideoView
            Log.d("imagenUrl", ApiConnection.baseUrl + contentModel.url)
            binding.imageView.visibility = View.GONE
        } else {
            // Si es una imagen, usar Glide para cargar la imagen en el ImageView
            Log.d("ImageUrl", ApiConnection.baseUrl + contentModel.url)
            binding.imageView.visibility = View.VISIBLE
            Glide.with(itemView.context)
                .load(ApiConnection.baseUrl + contentModel.url)
                .placeholder(R.drawable.logofinal2023)
                .into(binding.imageView)
        }

            binding.btnEdit.setOnClickListener {
                // Manejar el clic del botón para redirigir
                val intent = Intent(itemView.context, CrudUpdate1Activity::class.java)
                itemView.context.startActivity(intent)
            }
        clickListener()
        }





    private fun clickListener() {
        binding.btnEliminarproduct.setOnClickListener {
            Log.d(TAG, "ELIMINANDO..." + idabc)
            val apiGetContent = ApiConnection.getApiService().deleteProduct(idabc)
            val apiService = ApiConnection.getApiService()
            val deleteUserCall: Call<Void> = apiService.deleteProduct(idabc)
            deleteUserCall.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Eliminación exitosa
                        Log.d("User deletion", "User deleted successfully")

                    } else {
                        // Manejar errores en la respuesta, si es necesario
                        Log.e(
                            "User deletion",
                            "Failed to delete user. Response code: ${response.code()}"
                        )
                        // _deleteUserResult.value = false
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Manejar errores en la solicitud, si es necesario
                    Log.e("User deletion", "Error deleting user", t)
                    // _deleteUserResult.value = false
                }
            })
        }
    }





}
