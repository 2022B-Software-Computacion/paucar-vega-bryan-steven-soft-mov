package com.example.mercado_libre

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalles.*
import kotlinx.android.synthetic.main.activity_visulizar_producto.*

class Detalles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val producto = intent.getSerializableExtra("pro") as Producto

        iv_detalle_imagen.setImageResource(producto.idImagen)

        tv_detalle_nombre.text = getString(R.string.Nombre, producto.nombre)
        tv_detalle_costo.text = getString(R.string.Costo, producto.precio)
        tv_detalle_cuotas.text = getString(R.string.cuotas, producto.cuotas)
    }
}