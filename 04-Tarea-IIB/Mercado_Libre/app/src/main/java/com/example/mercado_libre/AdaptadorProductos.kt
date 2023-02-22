package com.example.mercado_libre

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mercado_libre.VisulizarProducto
import kotlinx.android.synthetic.main.activity_detalles.view.*
import kotlinx.android.synthetic.main.elemento_lista_producto.view.*

class AdaptadorProductos(private var lista:ArrayList<Producto>,private var contexto: Context): RecyclerView.Adapter<AdaptadorProductos.ViewHolder>(){


    class ViewHolder(var vista: View, var contexto:Context): RecyclerView.ViewHolder(vista){
        fun bind(producto: Producto){
            vista.iv_elemento_lista_producto.setImageResource(producto.idImagen)
            vista.tv_nombre_producto.text = producto.nombre
            vista.tv_precio_producto.text = producto.precio
            vista.tv_cuotas_producto.text = producto.cuotas

            vista.iv_elemento_lista_producto.setOnClickListener{
                contexto.startActivity(Intent(contexto, VisulizarProducto::class.java).putExtra("pro", producto))
            }

            vista.setOnClickListener{
                contexto.startActivity(Intent(contexto, Detalles::class.java).putExtra("pro", producto))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.elemento_lista_producto, parent, false)
        , contexto
        )
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, posicion: Int) {
        holder.bind(lista[posicion])
    }
}