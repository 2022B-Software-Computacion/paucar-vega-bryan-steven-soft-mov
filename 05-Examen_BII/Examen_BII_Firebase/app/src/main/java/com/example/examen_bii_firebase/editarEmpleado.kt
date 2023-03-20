package com.example.examen_bii_firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase

class editarEmpleado(
    id: Int?,
    toString: String,
    toString1: String,
    toString2: String,
    toInt: Int,
    cod: String?
) : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_empleado)

        val empleadoRecibido = intent.getParcelableExtra<Empleado>("editarEmpleado")

        var tvEmpleado = findViewById<TextView>(R.id.tv_EditarEmp)
        var etNombre = findViewById<TextView>(R.id.et_NombreEmp)
        var etApellido = findViewById<TextView>(R.id.et_ApellidoEmp)
        var etSueldo = findViewById<TextView>(R.id.et_SueldoEmp)
        var etEdad = findViewById<TextView>(R.id.et_edadEmp)

        tvEmpleado.text = empleadoRecibido!!.nombre + " - " + empleadoRecibido.apellido
        etNombre.text = empleadoRecibido!!.nombre
        etApellido.text = empleadoRecibido!!.apellido
        etSueldo.text = empleadoRecibido!!.sueldo
        etEdad.text = empleadoRecibido.edad.toString()

        var id = empleadoRecibido!!.id
        var cod = empleadoRecibido!!.codEmpresa

        val btnEditarEmp = findViewById<Button>(R.id.btn_AceptarEmp)
        btnEditarEmp
            .setOnClickListener {

                editarEmpleado(
                    id,
                    etNombre.text.toString(),
                    etApellido.text.toString(),
                    etSueldo.text.toString(),
                    etEdad.text.toString().toInt(),
                    cod
                )
                irActividad(editarEmpleado::class.java)

            }

        fun irActividad(
            clase: Class<*>
        ) {
            val intent = Intent(this, clase)
            startActivity(intent)
        }

        fun editarEmpleado(
            id: Int?,
            nombre: String?,
            apellido: String?,
            sueldo: String?,
            edad: Int?,
            codEmpresa: String?
        ) {
            val empleadoData = Empleado(
                id,
                nombre,
                apellido,
                sueldo,
                edad,
                codEmpresa
            )

            val db = Firebase.firestore
            val empleado = db.collection ("empleado")

            empleado.document(id.toString()).set(empleadoData)

    }
}

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
