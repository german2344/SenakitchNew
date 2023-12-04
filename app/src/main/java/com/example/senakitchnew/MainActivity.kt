package com.example.senakitchnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.senakitchnew.ImportClasses.popupalert

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this,my_inicio_activity::class.java)
            startActivity(intent)

        }, 6000)
    }
}