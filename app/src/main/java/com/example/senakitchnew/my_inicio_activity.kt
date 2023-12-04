package com.example.senakitchnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.databinding.ActivityLoginBinding
import com.example.senakitchnew.databinding.ActivityMyInicioBinding
class my_inicio_activity : AppCompatActivity() {

    private val toast = popupalert()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_inicio)

        val loginButtons = arrayOf(R.id.login, R.id.login2, R.id.login9, R.id.login4, R.id.login10)

        val intent = Intent(this, activity_login::class.java)

        loginButtons.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                startActivity(intent)
                showCustomToast("Botón presionado: ${findViewById<Button>(buttonId).text}")
            }
        }
    }

    private fun showCustomToast(message: String) {
        toast.toastSuccess(this, "Senakitch", "Binvenid@s")
    }


}

