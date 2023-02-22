package com.example.mercado_libre

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_Productos.layoutManager = LinearLayoutManager(this)
        rv_Productos.adapter =AdaptadorProductos(generarDatos(), this)
    }

    private fun generarDatos(): ArrayList<Producto>{
        val lista = ArrayList<Producto>()
        lista.add(
            Producto(R.drawable.producto1, "Laptop gamer Lenovo Legion 15ARH05", "USS: $949.99", "en 12 x USS 79.17 sin interéses")
        )
        lista.add(
            Producto(R.drawable.producto2, "Laptop Lenovo IdeaPad 15ALC6", "USS: $699.99", "en 12 x USS 58.33 sin interéses")
        )
        lista.add(
            Producto(R.drawable.producto3, "Apple MacBookPro", "USS: $1289.00", "en 12 x USS 107.42 sin interéses")
        )
        lista.add(
            Producto(R.drawable.producto4, "Lenovo Core i7 11va", "USS: $919.99", "en 12 x USS 76.67 sin interéses")
        )
        lista.add(
            Producto(R.drawable.producto5, "Laptop Lenovo Idea pad3", "USS: $1049.00", "en 12 x USS 87.42 sin interéses")
        )

        return lista
    }
}