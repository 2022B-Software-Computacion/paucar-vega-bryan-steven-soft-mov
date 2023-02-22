package com.example.mercado_libre

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalles.*
import kotlinx.android.synthetic.main.activity_visulizar_producto.*
import kotlinx.android.synthetic.main.elemento_lista_producto.*

class VisulizarProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visulizar_producto)

        val producto = intent.getSerializableExtra("pro") as Producto

        iv_foto_producto.setImageResource(producto.idImagen)
    }
}