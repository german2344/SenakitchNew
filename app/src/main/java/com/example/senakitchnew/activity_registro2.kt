package com.example.senakitchnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.util.PatternsCompat
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.bring.RegistroBring
import com.example.senakitchnew.databinding.ActivityRegistro2Binding
import com.example.senakitchnew.send.RegisterSend
import com.example.senakitchnew.ImportClasses.popupalert
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class activity_registro2 : AppCompatActivity() {

    private lateinit var binding: ActivityRegistro2Binding
    private val toast = popupalert()
    private val PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[@#$%^&+=!|°()?¡¿*.:,])(?=\\S+$).{8,}\$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistro2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.botonregistrar.setOnClickListener {
            if (validate()) {
                val name = binding.username.text.toString()
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                val passwordConfirm = binding.restorepassword.text.toString()
                registerUser(name, email, password, passwordConfirm)
            }
        }
        clickListener()

        binding.textnew.setOnClickListener {
            toast.toastSuccess(this, "Senakitch", "Iniciar sesion")
            startActivity(Intent(this, activity_login::class.java))
        }

        binding.textnew.setOnClickListener{
            toast.toastSuccess(this, "Senakitch", "Iniciar sesion")
            startActivity(Intent(this, activity_login::class.java))
        }
    }


    private fun clickListener() {
        binding.textnew.setOnClickListener{
            validate()

        }
    }



    private fun registerUser(name: String, email: String, password: String, passwordConfirm: String) {
        val registerRequest = RegistroBring(name, email, password, passwordConfirm)
        val apiCall = ApiConnection.getApiService().registrausuario(registerRequest)
        apiCall.enqueue(object : Callback<RegisterSend> {
            override fun onResponse(call: Call<RegisterSend>, response: Response<RegisterSend>) {
                if (response.isSuccessful) {
                    move()
                } else {
                    toast.toastError(this@activity_registro2, "Error", "Sucedio un error inesperado o corrige tus credenciales")
                }
            }

            override fun onFailure(call: Call<RegisterSend>, t: Throwable) {
                // Handle the failure case
            }
        })
    }
    private fun move() {
        if (validate()) {
            startActivity(Intent(this@activity_registro2, activity_login::class.java))
            toast.toastSuccess(this@activity_registro2, "SenaKitch", "Registrado con éxito!!!")
        } else {
            showIncompleteFieldsAlert()
        }
    }

    private fun showIncompleteFieldsAlert() {
        val builder = AlertDialog.Builder(this@activity_registro2)
        builder.setTitle("Campos Incompletos")
        builder.setMessage("Por favor, completa todos los campos antes de continuar.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }


    private fun validate(): Boolean {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val passwordConfirm = binding.restorepassword.text.toString()
        val name = binding.username.text.toString()

        val isEmailValid = !email.isEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = isPasswordValid(password, passwordConfirm)
        val isNameValid = !name.isEmpty()

        if (!isNameValid) {
            binding.username.error = "El campo no puede estar vacío"
        } else {
            binding.username.error = null
        }

        if (!isEmailValid) {
            binding.email.error = "Ingresa un correo válido"
        } else {
            binding.email.error = null
        }

        if (!isPasswordValid) {
            binding.password.error = "Las contraseñas deben coincidir y tener al menos 8 caracteres, incluyendo un caracter especial."
            binding.restorepassword.error = "Las contraseñas deben coincidir y tener al menos 8 caracteres, incluyendo un caracter especial."
        } else {
            binding.password.error = null
            binding.restorepassword.error = null
        }

        return isNameValid && isEmailValid && isPasswordValid
    }

    private fun isPasswordValid(password: String, passwordConfirm: String): Boolean {
        return password == passwordConfirm && password.length >= 8 && PASSWORD_PATTERN.matcher(password).matches()
    }
}
