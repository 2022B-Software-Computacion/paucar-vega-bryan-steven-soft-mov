package com.example.pbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("Edad", 0)
        val entrenador = intent.getParcelableExtra<BEntrenador>("entrenadorPrincipal")

        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton
            .setOnClickListener {devolverRespuesta()}
    }

    fun devolverRespuesta(){
        val intentDevolverParametro = Intent()
        intentDevolverParametro.putExtra("nombreModificador", "Vicente")
        intentDevolverParametro.putExtra("edadModificada", 45)
        setResult(
            RESULT_OK,
            intentDevolverParametro
        )
        finish()
    }
}