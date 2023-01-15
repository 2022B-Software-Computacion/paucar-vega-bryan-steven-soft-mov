package com.example.pbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
            irActividad(ACicloVida::class.java)
            }

        val botonlistView = findViewById<Button>(R.id.btn_ir_list_view)
        botonlistView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }
    }

    fun irActividad(
        clase: Class <*>
    ){
        val intent =  Intent(this, clase)
        startActivity(intent)
    }
}