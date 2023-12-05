package com.example.senakitchnew.Crud

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.Crud.MyCrudsActivity.MyCrudsViewHolder
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.MainActivity
import com.example.senakitchnew.R
import com.example.senakitchnew.bring.ProductBring
import com.example.senakitchnew.databinding.ActivityContentPostBinding
import com.example.senakitchnew.send.User
import com.example.senakitchnew.send.UserAdmin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrudPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContentPostBinding
    private val toast = popupalert()
    private lateinit var imageUri: Uri

    var userData: User? = null
    private var userId = 0

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageUri = it
            binding.imageUrl.setImageURI(it)

            val borderDrawable = ContextCompat.getDrawable(this, R.drawable.bordernavigation)
            binding.imageUrl.background = borderDrawable

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.image_preview)
                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)

            Glide.with(this)
                .load(imageUri)
                .apply(requestOptions)
                .centerCrop()
                .into(binding.imageUrl)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChooseImage.setOnClickListener {
            contract.launch("image/*")
        }

        binding.btnReturn.setOnClickListener {
            startActivity(Intent(this, MyCrudsViewHolder::class.java))
        }
        getUserProfile(userId.toString())
        createContent()
    }

//    @SuppressLint("Recycle")
//    private fun createContent() {
//        userId = UserAdmin.getUserId()
//        Log.d("CrudPostActivity", "User ID: $userId")
//
//        getUserProfile(userId.toString())
//
//        binding.btnUploadContent.setOnClickListener {
//            // Obtener las vistas
//
//            val filesDir = applicationContext.filesDir
//            val file = File(filesDir, "image.png")
//
//            val inputStream = imageUri.let { contentResolver.openInputStream(it) }
//            val outputStream = FileOutputStream(file)
//            inputStream!!.copyTo(outputStream)
//
//            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
//            val part = MultipartBody.Part.createFormData("url", file.name, requestBody)
//
//            val titleView = findViewById<EditText>(R.id.title)
//            val priceView = findViewById<EditText>(R.id.price)
//            val descriptionView = findViewById<EditText>(R.id.description)
//            val quantityView = findViewById<EditText>(R.id.quantity)
//
//            val title = titleView.text.toString()
//            val price = priceView.text.toString()
//            val description = descriptionView.text.toString()
//            val quantity =
//                if (quantityView.text.isNotEmpty()) quantityView.text.toString() else null
//
//            if (title.isNotEmpty() && price.isNotEmpty() && description.isNotEmpty() && quantity != null && user != null) {
//                val productBring = ProductBring(
//                    title,
//                    price,
//                    part,
//                    description,
//                    quantity,
//                )
//                Log.d("CrudPostActivity", "ProductBring: $productBring")
//
//                // Llamar a postContent sin multimediaPart
//                lifecycleScope.launch {
//                    try {
//                        performNetworkRequest(productBring)
//
//                        // Restablecer los campos después de la creación exitosa
//                        titleView.text.clear()
//                        priceView.text.clear()
//                        descriptionView.text.clear()
//                        quantityView.text.clear()
//
//                    } catch (e: Exception) {
//                        handleNetworkError(e)
//                    }
//                }
//            } else {
//                toast.toastWarning(
//                    this,
//                    "Campos incompletos",
//                    "Completa los campos y selecciona una imagen"
//                )
//            }
//        }
//    }
private fun createContent() {
    userId = UserAdmin.getUserId()

     getUserProfile(userId.toString())

    binding.btnUploadContent.setOnClickListener {
        val imageInputStream = contentResolver.openInputStream(imageUri)
        val imageRequestBody = imageInputStream?.use { input ->
            input.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
        }

        val name = binding.title.text.toString()
        val price = binding.price.text.toString()
        val description = binding.description.text.toString()
        val quantity = binding.quantity.text.toString()

        if (name.isNotEmpty() && price.isNotEmpty() && description.isNotEmpty() && quantity.isNotEmpty()) {
            val contentRequest = userData?.let { user ->
                ProductBring(
                    name,
                    price,
                    imageRequestBody,
                    description,
                    quantity,
                    user_id = user.id
                )
            }

            if (contentRequest != null) {
                postContent(contentRequest)
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





//    private suspend fun performNetworkRequest(contentRequest: ProductBring) {
//        val apiService = ApiConnection.getApiService()
//
//        val titleRequestBody = contentRequest.name.toRequestBody("text/plain".toMediaTypeOrNull())
//        val authorRequestBody =
//            contentRequest.price.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//
//        val descriptionRequestBody =
//            contentRequest.description.toRequestBody("text/plain".toMediaTypeOrNull())
//        val quantityRequestBody =
//            contentRequest.quantity?.toRequestBody("text/plain".toMediaTypeOrNull())
//
//
//        val productRequest = ProductBring(
//            name = contentRequest.name,
//            price = contentRequest.price,
//            image=contentRequest.image,
//            description = contentRequest.description,
//            quantity = contentRequest.quantity,
//        )
//
//        apiService.createProduct(productRequest).enqueue(object : Callback<ProductBring> {
//            override fun onResponse(call: Call<ProductBring>, response: Response<ProductBring>) {
//                if (response.isSuccessful) {
//                    val productResponse = response.body()
//                    toast.toastSuccess(
//                        this@CrudPostActivity,
//                        "Exitoso",
//                        "Producto creado exitosamente"
//                    )
//
//                    // Llamar a la función createContent después de obtener el usuario
//                    createContent()
//
//
//                    finish() // Opcional: finalizar la actividad actual si no quieres volver a ella
//                } else {
//                    // Manejar el error de la respuesta
//                    val errorBody = response.errorBody()?.string()
//                    Log.e("CrudPostActivity", "Error response body: $errorBody")
//                    toast.toastError(
//                        this@CrudPostActivity,
//                        "Error",
//                        "Por favor, llena todos los campos"
//                    )
//                }
//            }
//
//            override fun onFailure(call: Call<ProductBring>, t: Throwable) {
//                // Manejar la falla de la solicitud
//                Log.e("CrudPostActivity", "Error en la solicitud: ${t.localizedMessage}")
//                toast.toastError(
//                    this@CrudPostActivity,
//                    "Error",
//                    "Hubo un error en la solicitud"
//                )
//            }
//        })
//    }


    private fun postContent(contentRequest: ProductBring) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiConnection.getApiService()


                val titleRequestBody = contentRequest.name.toRequestBody("text/plain".toMediaTypeOrNull())
                val PriceRequestBody = contentRequest.price.toRequestBody("text/plain".toMediaTypeOrNull())

                val imageRequestBody = contentRequest.image!!
                val part = MultipartBody.Part.createFormData("url", "image_filename", imageRequestBody)


                val descriptionRequestBody = contentRequest.description.toRequestBody("text/plain".toMediaTypeOrNull())
                val quantityRequestBody = contentRequest.quantity!!.toRequestBody("text/plain".toMediaTypeOrNull())
                val userIdRequestBody = contentRequest.user_id.toString().toRequestBody("text/plain".toMediaTypeOrNull())

                val response = titleRequestBody.let {
                    apiService.createProduct(
                        titleRequestBody,
                        PriceRequestBody,
                        part!!,
                        descriptionRequestBody,
                        quantityRequestBody,
                        userIdRequestBody
                    ).execute()
                }

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        // Solicitud exitosa
                        toast.toastSuccess(this@CrudPostActivity, "Mis primeros auxilitos", "Contenido creado exitosamente, se revisará lo más pronto posible!!! 😊😊😊😊😊")
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    } else {
                        // Manejar error
                        toast.toastError(this@CrudPostActivity, "Error", "Por favor, llena todos los campos")
                    }
                }
            } catch (e: Exception) {

                // Manejar excepciones
                withContext(Dispatchers.Main) {
                    toast.toastError(this@CrudPostActivity, "Error", "e " + e.localizedMessage)
                }

            }
        }
    }


//
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
                    userData = response.body()
                    userData?.let {
                        Log.d("CrudPostActivity", "User: $userData")
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

