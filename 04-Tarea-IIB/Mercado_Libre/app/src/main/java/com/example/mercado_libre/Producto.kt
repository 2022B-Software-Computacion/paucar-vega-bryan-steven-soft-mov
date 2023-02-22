package com.example.mercado_libre

import java.io.Serializable

data class Producto  (
    var idImagen : Int,
    var nombre: String,
    var precio: String,
    var cuotas: String
        ): Serializable

