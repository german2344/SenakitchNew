package com.example.senakitchnew.Crud

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.MainActivity
import com.example.senakitchnew.bring.ProductBring
import com.example.senakitchnew.databinding.ActivityContentUpdateBinding
import com.example.senakitchnew.send.ProductSend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CrudUpdateActivity : AppCompatActivity() {

  private val toast = popupalert()
  private var tipoAsignaturaId = 0
  private lateinit var binding: ActivityContentUpdateBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityContentUpdateBinding.inflate(layoutInflater)
    setContentView(binding.root)

    getIdTipoAsignacion()
    getTipoAsignaturaById(tipoAsignaturaId.toString())
    sendContentToUpdate()
  }

  private fun getIdTipoAsignacion() {
    val intent = intent
    if (intent != null && intent.hasExtra("TIPOASIGNATURA_ID")) {
      tipoAsignaturaId = intent.getIntExtra("TIPOASIGNATURA_ID", 0)
      Log.e("TIPO", "$tipoAsignaturaId")
    } else {
      toast.toastError(
        this,
        "Error",
        "El valor de TIPOASIGNATURA_ID es nulo o no se ha proporcionado"
      )
    }
  }

  private fun getTipoAsignaturaById(idProduct: String) {
    val apiService = ApiConnection.getApiService()
    val tipoAsignatura: Call<ProductSend> = apiService.getProductById(idProduct)

    tipoAsignatura.enqueue(object : Callback<ProductSend> {
      override fun onResponse(call: Call<ProductSend>, response: Response<ProductSend>) {
        if (response.isSuccessful) {
          val tipoAsignatura = response.body()
          tipoAsignatura?.let {
            Log.d("API_RESPONSE", "Received data: $tipoAsignatura")
            binding.titleUpdate.setText(it.name)
            binding.preci.setText(it.price)
            binding.description.setText(it.description)
            binding.quantity.setText(it.quantity)
          }
        } else {
          toast.toastError(
            this@CrudUpdateActivity,
            "Error de respuesta",
            "La respuesta del servidor no fue exitosa"
          )
        }
      }

      override fun onFailure(call: Call<ProductSend>, t: Throwable) {
        toast.toastError(
          this@CrudUpdateActivity,
          "Conexión",
          "Error de conexión: ${t.message}"
        )
      }
    })
  }

  private fun sendContentToUpdate() {
    binding.actualizar.setOnClickListener {
      val title = binding.titleUpdate.text.toString()
      val preci = binding.preci.text.toString()
      val description = binding.description.text.toString()
      val quantity = binding.quantity.text.toString()

      if (title.isNotEmpty() && description.isNotEmpty()) {
        val tipoAsignaturaRequest = ProductBring(title, preci, description, quantity)
        updateContent(tipoAsignaturaRequest)
      } else {
        toast.toastWarning(
          this,
          "Campos incompletos",
          "Completa los campos y selecciona una imagen"
        )
      }
    }
  }

  private fun updateContent(tipoAsignaturaRequest: ProductBring) {
    CoroutineScope(Dispatchers.IO).launch {
      try {
        val apiService = ApiConnection.getApiService()
        val requestBody = ProductBring(
          name = tipoAsignaturaRequest.name,
          price = tipoAsignaturaRequest.price,
          description = tipoAsignaturaRequest.description,
          quantity = tipoAsignaturaRequest.quantity
        )

        val response = apiService.actualizarProduct(
          tipoAsignaturaId = tipoAsignaturaId.toString(),
          body = requestBody
        ).execute()

        withContext(Dispatchers.Main) {
          if (response.isSuccessful) {
            toast.toastSuccess(
              this@CrudUpdateActivity,
              "Mis primeros auxilios",
              "Contenido actualizado exitosamente, se revisará lo más pronto posible!!! 😊😊😊😊😊"
            )
            startActivity(Intent(applicationContext, MainActivity::class.java))
          } else {
            toast.toastError(
              this@CrudUpdateActivity,
              "Error",
              "Por favor, llena todos los campos"
            )
          }
        }
      } catch (e: Exception) {
        withContext(Dispatchers.Main) {
          toast.toastError(
            this@CrudUpdateActivity,
            "Error",
            "e ${e.localizedMessage}"
          )
        }
      }
    }
  }

}
