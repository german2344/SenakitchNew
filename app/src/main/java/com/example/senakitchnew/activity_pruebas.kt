package com.example.senakitchnew

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.util.PatternsCompat
import com.bumptech.glide.Glide
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.ImportClasses.KeyBoard
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.Services.ApiService
import com.example.senakitchnew.databinding.ActivityPruebasBinding
import com.example.senakitchnew.send.User
import com.example.senakitchnew.send.UserAdmin
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response

class activity_pruebas : AppCompatActivity() {
    private lateinit var binding: ActivityPruebasBinding
    private lateinit var profileImage: CircleImageView

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var tvEmail: TextView
    private lateinit var viewRoot: LinearLayout
    private var keyBoard = KeyBoard()
    private val toast = popupalert()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPruebasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        binding.actualizar.setOnClickListener {
            startActivity(Intent(this, ActivityPruebasBinding::class.java))
        }

        initData()
    }

    private fun initData() {

        val userId = UserAdmin.getUserId()

        getUserProfile(userId.toString())

        profileImage = findViewById(R.id.profile_image)

        keyBoard

        /*binding.btnRegresar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }*/

        binding.edit.setOnClickListener {
            Dialog()
        }

        binding.delete.setOnClickListener {

            //val deleteUser = ApiClient.getApiService().deleteUser()
            toast.toastError(this, "Mis Primeros Auxilitos", "Perfil eliminado")
            //val deleteUser =

        }
    }

    fun getUserProfile(userId: String) {
        val apiService = ApiConnection.getApiService()

        val userProfileCall: Call<User> = apiService.getUserProfile(userId)
        userProfileCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        findViewById<TextView>(R.id.username).text = it.name
                        findViewById<TextView>(R.id.Correo).text = it.email
                        findViewById<TextView>(R.id.telefono).text = it.description

                        Glide.with(this@activity_pruebas)
                            .load(it.profile_photo_url)
                            .placeholder(R.drawable.logo) // Imagen de carga mientras se carga la imagen
                            .error(R.drawable.logo) // Imagen de error si no se puede cargar la imagen
                            .into(profileImage)

                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                toast.toastError(this@activity_pruebas, "Conexión", "Error de conexión")
            }
        })
    }

    //    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables", "SuspiciousIndentation")
    @SuppressLint("MissingInflatedId")
    private fun Dialog() {
        // Inflate the dialog's view
        val view = layoutInflater.inflate(R.layout.fragment_gallery, null)

        // Initialize name, email, and tvEmail
        name = view.findViewById(R.id.username)
        email = view.findViewById(R.id.Correo)


        val alertDialog = MaterialAlertDialogBuilder(this)
        alertDialog.setTitle(resources.getString(R.string.mensaje))
        alertDialog.setIcon(R.drawable.logo)

        alertDialog.setView(view)

        val dialog: AlertDialog = alertDialog.create()
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            if (validateEmail()) {
                if (name.text.toString().isEmpty() && email.text.toString().isNotEmpty()) {
                    toast.toastSuccess(this, "Perfil", "Perfil editado correctamente")
                    dialog.dismiss()
                }
            }
        }
    }

    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(viewRoot.windowToken, 0)
    }

    @SuppressLint("SetTextI18n")
    private fun validateEmail(): Boolean {
        val email = email.text.toString()
        return if (email.isEmpty()) {
            tvEmail.text = "El campo del correo no puede estar vacio"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            tvEmail.text = "Por favor ingresa un correo valido"
            false
        } else {
            tvEmail.text = null
            true
        }
    }

}
