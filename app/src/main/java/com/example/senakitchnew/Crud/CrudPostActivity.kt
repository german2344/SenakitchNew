package com.example.senakitchnew.Crud

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.Crud.MyCrudsActivity.MyCrudsViewHolder
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.R
import com.example.senakitchnew.bring.ProductBring
import com.example.senakitchnew.databinding.ActivityContentPostBinding
import com.example.senakitchnew.send.User
import com.example.senakitchnew.send.UserAdmin
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrudPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContentPostBinding
    private val toast = popupalert()
    private lateinit var imageUri: Uri

    private var user: User? = null
    private var userId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReturn.setOnClickListener {
            startActivity(Intent(this, MyCrudsViewHolder::class.java))
        }
        getUserProfile(userId.toString())
        createContent()
    }

    @SuppressLint("Recycle")
    private fun createContent() {
        userId = UserAdmin.getUserId()
        Log.d("CrudPostActivity", "User ID: $userId")

        getUserProfile(userId.toString())

        binding.btnUploadContent.setOnClickListener {
            // Obtener las vistas
            val titleView = findViewById<EditText>(R.id.title)
            val priceView = findViewById<EditText>(R.id.price)
            val descriptionView = findViewById<EditText>(R.id.description)
            val quantityView = findViewById<EditText>(R.id.quantity)

            val title = titleView.text.toString()
            val price = priceView.text.toString()
            val description = descriptionView.text.toString()
            val quantity =
                if (quantityView.text.isNotEmpty()) quantityView.text.toString() else null

            if (title.isNotEmpty() && price.isNotEmpty() && description.isNotEmpty() && quantity != null && user != null) {
                // Manejar la URL nula proporcionando un valor predeterminado
                val defaultUrl =
                    "https://example.com/default.jpg" // Cambia esto por tu URL predeterminada
                val productBring = ProductBring(
                    title,
                    price,
                    description,
                    quantity,
                )

                Log.d("CrudPostActivity", "ProductBring: $productBring")

                // Llamar a postContent sin multimediaPart
                lifecycleScope.launch {
                    try {
                        performNetworkRequest(productBring)

                        // Restablecer los campos después de la creación exitosa
                        titleView.text.clear()
                        priceView.text.clear()
                        descriptionView.text.clear()
                        quantityView.text.clear()

                    } catch (e: Exception) {
                        handleNetworkError(e)
                    }
                }
            } else {
                toast.toastWarning(
                    this,
                    "Campos incompletos",
                    "Completa los campos y selecciona una imagen"
                )
            }
        }
    }




    private suspend fun performNetworkRequest(contentRequest: ProductBring) {
        val apiService = ApiConnection.getApiService()

        val titleRequestBody = contentRequest.name.toRequestBody("text/plain".toMediaTypeOrNull())
        val authorRequestBody =
            contentRequest.price.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        val descriptionRequestBody =
            contentRequest.description.toRequestBody("text/plain".toMediaTypeOrNull())
        val quantityRequestBody =
            contentRequest.quantity?.toRequestBody("text/plain".toMediaTypeOrNull())


        val productRequest = ProductBring(
            name = contentRequest.name,
            price = contentRequest.price,
            description = contentRequest.description,
            quantity = contentRequest.quantity,
        )

        apiService.createProduct(productRequest).enqueue(object : Callback<ProductBring> {
            override fun onResponse(call: Call<ProductBring>, response: Response<ProductBring>) {
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    toast.toastSuccess(
                        this@CrudPostActivity,
                        "Exitoso",
                        "Producto creado exitosamente"
                    )

                    // Llamar a la función createContent después de obtener el usuario
                    createContent()


                    finish() // Opcional: finalizar la actividad actual si no quieres volver a ella
                } else {
                    // Manejar el error de la respuesta
                    val errorBody = response.errorBody()?.string()
                    Log.e("CrudPostActivity", "Error response body: $errorBody")
                    toast.toastError(
                        this@CrudPostActivity,
                        "Error",
                        "Por favor, llena todos los campos"
                    )
                }
            }

            override fun onFailure(call: Call<ProductBring>, t: Throwable) {
                // Manejar la falla de la solicitud
                Log.e("CrudPostActivity", "Error en la solicitud: ${t.localizedMessage}")
                toast.toastError(
                    this@CrudPostActivity,
                    "Error",
                    "Hubo un error en la solicitud"
                )
            }
        })
    }
    private fun handleNetworkError(error: Exception) {
        // Manejar el error de la red aquí
        Log.e("CrudPostActivity", "Error de red: ${error.localizedMessage}")
        toast.toastError(
            this@CrudPostActivity,
            "Error",
            "Hubo un error en la solicitud"
        )
    }
    private fun getUserProfile(userId: String) {
        val apiService = ApiConnection.getApiService()

        val userProfileCall: Call<User> = apiService.getUserProfile(userId)
        userProfileCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    user = response.body()
                    user?.let {
                        Log.d("CrudPostActivity", "User: $user")
                        createContent() // Llamar a la función createContent después de obtener el usuario
                    } ?: run {
                        Log.e("CrudPostActivity", "User is null")
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Error user", t.toString())
            }
        })
    }


}

