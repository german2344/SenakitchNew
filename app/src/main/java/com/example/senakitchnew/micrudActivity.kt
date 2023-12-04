package com.example.senakitchnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.databinding.ActivityMicrudBinding
import com.example.senakitchnew.send.ProductSend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class micrudActivity : AppCompatActivity(), UsuarioAdapter.OnItemClicked {
    //    lateinit var binding: ActivityMicrudBinding
//
//    lateinit var adatador: UsuarioAdapter
//
//    var listaUsuarios = arrayListOf<ProductSend>()
//
//
////    var usuario = ProductSend(-1, "","","","","","","")
//
//    var isEditando = false
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMicrudBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.rvUsuarios.layoutManager = LinearLayoutManager(this)
//        setupRecyclerView()
//
//        obtenerUsuarios()
//
//        binding.btnAddUpdate.setOnClickListener {
//            var isValido = validarCampos()
//            if (isValido) {
//                if (!isEditando) {
//                    agregarUsuario()
//                } else {
//                    actualizarUsuario()
//                }
//            } else {
//                Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
//            }
//        }
//
//    }
//
//    fun setupRecyclerView() {
//        adatador = UsuarioAdapter(this, listaUsuarios)
//        adatador.setOnClick(this@micrudActivity)
//        binding.rvUsuarios.adapter = adatador
//
//    }
//
//    fun validarCampos(): Boolean {
//        return !(binding.etNombre.text.isNullOrEmpty() || binding.etPrice.text.isNullOrEmpty() || binding.etImage.text.isNullOrEmpty()|| binding.etSlug.text.isNullOrEmpty()|| binding.etUrl.text.isNullOrEmpty()|| binding.etAutor.text.isNullOrEmpty()|| binding.etdescription.text.isNullOrEmpty())
//    }
//
////    fun obtenerUsuarios() {
////        CoroutineScope(Dispatchers.IO).launch {
////            val call = RetrofitClient.webService.obtenerUsuarios()
////            runOnUiThread {
////                if (call.isSuccessful) {
////                    listaUsuarios = call.body()!!.listaUsuarios
////                    setupRecyclerView()
////                } else {
////                    Toast.makeText(this@micrudActivity, "ERROR CONSULTAR TODOS", Toast.LENGTH_LONG).show()
////                }
////            }
////        }
////    }
//// ...
//
//    fun obtenerUsuarios() {
//        val apiService = ApiConnection.getApiService()
//
//        val obtenerUsuariosCall: Call<List<ProductSend>> = apiService.getProduct()
//        obtenerUsuariosCall.enqueue(object : Callback<List<ProductSend>> {
//            override fun onResponse(call: Call<List<ProductSend>>, response: Response<List<ProductSend>>) {
//                if (response.isSuccessful) {
//                    val usuarios = response.body()
//                    usuarios?.let {
//                        listaUsuarios = ArrayList(it)
//                        adatador.notifyDataSetChanged()
//
//                        Log.d("ObtenerUsuarios", "Usuarios obtenidos correctamente. Cantidad: ${listaUsuarios.size}")
//                        for (usuario in listaUsuarios) {
//                            Log.d("ObtenerUsuarios", "ID de usuario: ${usuario.id}")
//                            Log.d("ObtenerUsuarios", "Usuario: $usuario")
//                        }
//                    }
//                } else {
//                    handleError("Error al obtener usuarios: ${response.message()}")
//                    Log.e("ObtenerUsuarios", "Error en la respuesta: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<List<ProductSend>>, t: Throwable) {
//                handleError("Error al obtener usuarios: ${t.message}")
//                Log.e("ObtenerUsuarios", "Error en la llamada: ${t.localizedMessage}")
//            }
//        })
//    }
//
//
//
//// ...
//
//
//
////    fun agregarUsuario() {
////
////        this.usuario.id = -1
////        this.usuario.name = binding.etNombre.text.toString()
////        this.usuario.description = binding.etEmail.text.toString()
////
////        CoroutineScope(Dispatchers.IO).launch {
////            val call = RetrofitClient.webService.agregarUsuario(usuario)
////            runOnUiThread {
////                if (call.isSuccessful) {
////                    Toast.makeText(this@MainActivity, call.body().toString(), Toast.LENGTH_LONG).show()
////                    obtenerUsuarios()
////                    limpiarCampos()
////                    limpiarObjeto()
////
////                } else {
////                    Toast.makeText(this@MainActivity, "ERROR ADD", Toast.LENGTH_LONG).show()
////                }
////            }
////        }
////    }
//fun agregarUsuario() {
//    val nombre = binding.etNombre.text.toString()
//    val price = binding.etPrice.text.toString()
//    val image = binding.etImage.text.toString()
//
//    val slug = binding.etSlug.text.toString()
//    val url = binding.etUrl.text.toString()
//    val autor = binding.etAutor.text.toString()
//    val description = binding.etdescription.text.toString()
//
//    if (nombre.isNotEmpty() && price.isNotEmpty()) {
//        val nuevoProducto = ProductSend(
//            id = -1,
//            name = nombre,
//            description = description,
//            price = price,
//            image = image,
//            slug = slug,
//            url = url,
//            autor = autor
//        )
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val apiService = ApiConnection.getApiService()
//                val call = apiService.agregarUsuario(nuevoProducto) // Llamada síncrona
//
//                withContext(Dispatchers.Main) {
//                    if (call.isSuccessful) {
//                        Toast.makeText(this@micrudActivity, call.body().toString(), Toast.LENGTH_LONG).show()
//                        obtenerUsuarios()
//                        limpiarCampos()
//                        limpiarObjeto()
//                    } else {
//                        handleError("Error ADD")
//                    }
//                }
//            } catch (e: Exception) {
//                handleError("Error: ${e.message}")
//            }
//        }
//    } else {
//        handleError("Please enter valid name and price")
//    }
//}
//
//
//    private fun handleError(message: String) {
//        runOnUiThread {
//            Toast.makeText(this@micrudActivity, message, Toast.LENGTH_LONG).show()
//        }
//    }
//
//
//
//
//    //    fun actualizarUsuario() {
////
////        this.usuario.name = binding.etNombre.text.toString()
////        this.usuario.price = binding.etPrice.text.toString()
////        this.usuario.image = binding.etImage.text.toString()
////        this.usuario.slug = binding.etSlug.text.toString()
////        this.usuario.url = binding.etUrl.text.toString()
////        this.usuario.autor = binding.etAutor.text.toString()
////        this.usuario.description = binding.etdescription.text.toString()
////
////        CoroutineScope(Dispatchers.IO).launch {
////            val call = RetrofitClient.webService.actualizarUsuario(usuario.idUsuario, usuario)
////            runOnUiThread {
////                if (call.isSuccessful) {
////                    Toast.makeText(this@MainActivity, call.body().toString(), Toast.LENGTH_LONG).show()
////                    obtenerUsuarios()
////                    limpiarCampos()
////                    limpiarObjeto()
////
////                    binding.btnAddUpdate.setText("Agregar Usuario")
////                    binding.btnAddUpdate.backgroundTintList = resources.getColorStateList(R.color.green)
////                    isEditando = false
////                }
////            }
////        }
////    }
//fun actualizarUsuario() {
//    val nuevoNombre = binding.etNombre.text.toString()
//    val nuevoPrice = binding.etPrice.text.toString()
//    val nuevaImage = binding.etImage.text.toString()
//    val nuevoSlug = binding.etSlug.text.toString()
//    val nuevaUrl = binding.etUrl.text.toString()
//    val nuevoAutor = binding.etAutor.text.toString()
//    val nuevaDescription = binding.etdescription.text.toString()
//
//    if (nuevoNombre.isNotEmpty() && nuevoPrice.isNotEmpty()) {
//        val nuevoUsuario = ProductSend(
//            id = usuario.id,
//            name = nuevoNombre,
//            price = nuevoPrice,
//            image = nuevaImage,
//            slug = nuevoSlug,
//            url = nuevaUrl,
//            autor = nuevoAutor,
//            description = nuevaDescription
//        )
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val apiService = ApiConnection.getApiService()
//                val call = if (isEditando) {
//                    apiService.actualizarProducto(nuevoUsuario.id, nuevoUsuario)
//                } else {
//                    apiService.agregarUsuario(nuevoUsuario)
//                }
//
//                withContext(Dispatchers.Main) {
//                    if (call.code() == 200) {
//                        val mensaje = if (isEditando) "Usuario actualizado" else "Usuario agregado"
//                        Toast.makeText(this@micrudActivity, mensaje, Toast.LENGTH_LONG).show()
//                        obtenerUsuarios()
//                        limpiarCampos()
// limpiarObjeto()
//
//                        binding.btnAddUpdate.text = "Agregar Usuario"
//                        binding.btnAddUpdate.backgroundTintList = resources.getColorStateList(R.color.green)
//                        isEditando = false
//                    } else {
//                        handleError("Error al actualizar/agregar usuario: ${call.errorBody()?.string()}")
//                    }
//                }
//            } catch (e: Exception) {
//                handleError("Error: ${e.message}")
//            }
//        }
//    } else {
//        handleError("Por favor, ingrese un nombre y un precio válidos")
//    }
//}
//
//
//
//    fun limpiarCampos() {
//        binding.etNombre.setText("")
//        binding.etPrice.setText("")
//        binding.etImage.setText("")
//        binding.etSlug.setText("")
//        binding.etUrl.setText("")
//        binding.etAutor.setText("")
//        binding.etdescription.setText("")
//
//
//    }
//
//    fun limpiarObjeto() {
//        // Restablecer los campos de usuario a sus valores iniciales
//        usuario.id = -1
//        usuario.name = ""
//        usuario.price = "0.0"
//        usuario.image = ""
//        usuario.slug = ""
//        usuario.autor = ""
//        usuario.description = ""
//    }
//
//
//
//    override fun editarProducto(producto: ProductSend) {
//        binding.etNombre.setText(producto.name)
//        binding.etPrice.setText(producto.price)
//        binding.etImage.setText(producto.image)
//        binding.etSlug.setText(producto.slug)
//        binding.etAutor.setText(producto.autor)
//        binding.etdescription.setText(producto.description)
//        binding.btnAddUpdate.setText("Actualizar Usuario")
//        binding.btnAddUpdate.backgroundTintList = resources.getColorStateList(R.color.green)
//        this.usuario = producto
//        isEditando = true
//    }
//
//    override fun borrarProducto(idProducto: Int) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val apiService = ApiConnection.getApiService()
//                val call = apiService.borrarUsuario(idProducto)
//
//                withContext(Dispatchers.Main) {
//                    if (call.isSuccessful) {
//                        Toast.makeText(this@micrudActivity, "Usuario eliminado", Toast.LENGTH_LONG).show()
//                        obtenerUsuarios()
//                    } else {
//                        handleError("Error al borrar usuario: ${call.errorBody()?.string()}")
//                    }
//                }
//            } catch (e: Exception) {
//                handleError("Error: ${e.message}")
//            }
//        }
//    }
    override fun editarProducto(usuario: ProductSend) {
        TODO("Not yet implemented")
    }

    override fun borrarProducto(idUsuario: Int) {
        TODO("Not yet implemented")
    }


}