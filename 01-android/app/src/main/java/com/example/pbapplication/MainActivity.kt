package com.example.pbapplication

<<<<<<< HEAD
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
=======
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
>>>>>>> main

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< HEAD
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
            irActividad(ACicloVida::class.java)}
    }

    fun irActividad(
        clase: Class <*>
    ){
        val intent =  Intent(this, clase)
        startActivity(intent)
=======
>>>>>>> main
    }
}