package com.example.senakitchnew.Crud.MyCrudsActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.R
import com.example.senakitchnew.bring.ProductBring
import com.example.senakitchnew.databinding.ActivityContentUpdateBinding
import com.example.senakitchnew.send.ProductSend
import com.example.senakitchnew.send.User
import com.example.senakitchnew.send.UserAdmin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.text.toIntOrNull

class CrudUpdate1Activity : AppCompatActivity() {
//    private lateinit var binding: ActivityContentUpdateBinding
//    private val toast = popupalert()
//
//    private var contentId = 0
//
//    private var myContentToUpdate: ProductSend? = null
//
//    private var imageUriToUpdate: Uri? = null
//
//    private var userData: User? = null
//    private var userId = 0
//
//    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//        uri?.let {
//            imageUriToUpdate = it
//            //binding.imageUrlUpdate.setImageURI(it)
//
//            val borderDrawable = ContextCompat.getDrawable(this, R.drawable.bordernavigation) // Obtén el fondo redondeado desde los recursos
//            val requestOptions = RequestOptions()
//                .placeholder(R.drawable.image_preview) // Imagen de placeholder mientras se carga la imagen
//                .error(R.drawable.error) // Imagen de error si la carga falla
//                .diskCacheStrategy(DiskCacheStrategy.NONE) // Evita el almacenamiento en caché de la imagen para que se vuelva a cargar cada vez
//            Glide.with(this)
//                .load(imageUriToUpdate)
//                .apply(requestOptions)
//                .centerCrop() // Escala la imagen para llenar el área del ImageButton mientras mantiene las proporciones y corta el exceso
//            //.into(binding.imageUrlUpdate)
//
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityContentUpdateBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
////    binding.btnChooseImage.setOnClickListener {
////      contract.launch("image/*")
////    }
//
//        getIdMyContent()
//
//        getMyContentById(contentId)
//
////    binding.btnReturn.setOnClickListener {
////      startActivity(Intent(this, MyCrudsActivity::class.java))
////    }
//
//        sendContentToUpdate()
//
//        binding.btnDeleteContent.setOnClickListener {
//            deleteContent(contentId.toString())
//            startActivity(Intent(this@CrudUpdate1Activity, MyCrudsActivity::class.java))
//        }
//
//    }
//
//    private fun deleteContent(idContent: String) {
//        deleteContentById(idContent)
//    }
//
//    private fun getIdMyContent() {
//        val intent = intent
//        if (intent != null && intent.hasExtra("CONTENIDO_ID")) {
//            contentId = intent.getStringExtra("CONTENIDO_ID")?.let { Integer.parseInt(it) }!!
//        } else {
//            toast.toastError(this, "Error", "Ups!, ha ocurrido un error inesperado, intentalo de nuevo o más tarde")
//        }
//    }
//
//
//    @SuppressLint("Recycle")
//    private fun sendContentToUpdate() {
//        userId = UserAdmin.getUserId()
//        getUserProfile(userId.toString())
//
//        binding.btnUploadContent.setOnClickListener {
//
//            val title = binding.titleUpdate.text.toString()
//            val description = binding.descriptionUpdate.text.toString()
//
//            if (title.isNotEmpty() && description.isNotEmpty()) {
//
//                // Object of content
//                val contentRequest = userData?.let { user ->
//                    ProductBring(
//                        name = title,
//                        price = "", // Puedes asignar un valor predeterminado o modificar según tu lógica
//                        description = description,
//                        quantity = "", // Puedes asignar un valor predeterminado o modificar según tu lógica
//                        user_id = myContentToUpdate?.user_id?.toIntOrNull() ?: 0
//                    )
//                }
//
//                // Llamar a la función para enviar los datos al servidor
//                if (contentRequest != null) {
//                    Log.e("CONTENT", "$contentRequest")
//                    updateContent(contentRequest)
//                }
//
//            } else {
//                toast.toastWarning(this, "Campos incompletos", "Completa los campos")
//            }
//        }
//    }
//
//
//    private fun updateContent(contentRequest: ProductBring) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val apiService = ApiConnection.getApiService()
//
//                val response = apiService.updateProduct(
//                    contentRequest,
//                    contentRequest.user_id.toString()
//                ).execute()
//
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        // Solicitud exitosa
//                        toast.toastSuccess(
//                            this@CrudUpdate1Activity,
//                            "Mis primeros auxilios",
//                            "Contenido actualizado exitosamente, se revisará lo más pronto posible!!! 😊😊😊😊😊"
//                        )
//                        startActivity(Intent(applicationContext, MyCrudsActivity::class.java))
//                    } else {
//                        // Manejar error
//                        toast.toastError(
//                            this@CrudUpdate1Activity,
//                            "Error",
//                            "Por favor, llena todos los campos"
//                        )
//                    }
//                }
//            } catch (e: Exception) {
//                // Manejar excepciones
//                withContext(Dispatchers.Main) {
//                    toast.toastError(
//                        this@CrudUpdate1Activity,
//                        "Error",
//                        "Error: ${e.localizedMessage}"
//                    )
//                }
//            }
//        }
//    }
//
//
//
//
//
//    /**
//     *  Get data of User by id login
//     */
//    private fun getUserProfile(userId: String) {
//        val apiService = ApiConnection.getApiService()
//
//        val userProfileCall: Call<User> = apiService.getUserProfile(userId)
//        userProfileCall.enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if (response.isSuccessful) {
//                    userData = response.body()
//                    userData?.let {}
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                toast.toastError(this@CrudUpdate1Activity, "Conexión", "Error de conexión")
//            }
//        })
//    }
//
//    private fun getMyContentById(contentId: Int)
//    {
//        val apiService = ApiConnection.getApiService()
//
//        val myContentCall: Call<ProductSend> = apiService.getOneContent(contentId.toString())
//        myContentCall.enqueue(object : Callback<ProductSend> {
//            @SuppressLint("SetTextI18n")
//            override fun onResponse(call: Call<ProductSend>, response: Response<ProductSend>) {
//                if (response.isSuccessful) {
//                    val myContent = response.body()
//                    myContentToUpdate = myContent
//                    myContent?.let {
//                        findViewById<TextView>(R.id.title_update).text          = it.name
//                        findViewById<TextView>(R.id.description_update).text    = it.description
//                        Glide.with(this@CrudUpdate1Activity)
//                            .load(ApiConnection.baseUrl + it.url)
//                            .placeholder(R.drawable.logo)
//                            .error(R.drawable.logo)
//                            .into(binding.imageView)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ProductSend>, t: Throwable) {
//                toast.toastError(this@CrudUpdate1Activity, "Conexión", "Error de conexión")
//            }
//        })
//    }
// cual archio es? German ese esas de ahi
//    private fun deleteContentById(contentId: String) {
//        val apiService = ApiConnection.getApiService()
//
//        apiService.deleteContent(contentId).enqueue(object : Callback<Void> {
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                if (response.isSuccessful) {
//                    toast.toastSuccess(this@CrudUpdate1Activity, "Contenido", "El contenido fue eliminado exitosamente!!!")
//                } else {
//                    toast.toastError(this@CrudUpdate1Activity, "Contenido", "Ups, ha ocurrido un error inesperado")
//                }
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                toast.toastError(this@CrudUpdate1Activity, "Error", "Error de conexión")
//            }
//        })
//    }
}