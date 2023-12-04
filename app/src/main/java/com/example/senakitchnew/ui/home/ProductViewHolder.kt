package com.example.senakitchnew.ui.home

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.R
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



    fun render(contentModel: ProductSend, onClickListener:(ProductSend) -> Unit) {
        binding.title.text = contentModel.name
        binding.Preci.text = contentModel.price
        binding.description.text = contentModel.description
        binding.quantity.text = contentModel.quantity

        // Verificar si la URL es para un video o una imagen
//        if (contentModel.url?.matches(Regex(".+\\.(mp4|avi|mov|mkv|wmv|flv|webm)$", RegexOption.IGNORE_CASE)) == true) {
//            // Si es un video, usar VideoView
//            Log.d("imagenUrl", ApiConnection.baseUrl + contentModel.url)
//            binding.imageView.visibility = View.GONE
//        } else {
//            // Si es una imagen, usar Glide para cargar la imagen en el ImageView
//            Log.d("ImageUrl", ApiConnection.baseUrl + contentModel.url)
//            binding.imageView.visibility = View.VISIBLE
//            Glide.with(itemView.context)
//                .load(ApiConnection.baseUrl + contentModel.url)
//                .placeholder(R.drawable.logofinal2023)
//                .into(binding.imageView)
//        }

        binding.btnEdit.setOnClickListener{
            onClickListener(contentModel)
        }
        clickListener(contentModel.id)
        }





    private fun clickListener(id: Int) {
        binding.btnEliminarproduct.setOnClickListener {
            // Registro de información sobre la eliminación
            Log.d(TAG, "ELIMINANDO... $id")

            // Llamada a la API para eliminar un tipo de asignatura
            val apiService = ApiConnection.getApiService()
            val deleteUserCall: Call<Void> = apiService.deleteProduct(id.toString())

            // Manejo de la respuesta de la llamada a la API
            deleteUserCall.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Eliminación exitosa
                        Log.d("TipoAsignatura deletion", "TipoAsignatura deleted successfully")
                    } else {
                        // Manejar errores en la respuesta, si es necesario
                        Log.e(
                            "TipoAsignatura deletion",
                            "Failed to delete TipoAsignatura. Response code: ${response.code()}"
                        )
                        // Muestra una alerta de error
                        toast.toastError(itemView.context, "Error", "Error eliminando TipoAsignatura")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Manejar errores en la solicitud, si es necesario
                    Log.e("TipoAsignatura deletion", "Error deleting TipoAsignatura", t)
                    // Muestra una alerta de error
                    toast.toastError(itemView.context, "Error", "Error eliminando TipoAsignatura")
                }
            })
        }
    }





}
