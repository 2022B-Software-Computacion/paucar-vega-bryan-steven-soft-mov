package com.example.examen_bii_firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase

class editarEmpresa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_empresa)

        val facultadRecibida = intent.getParcelableExtra<Empresa>("editarEmpresa")

        var tvEditar = findViewById<TextView>(R.id.tv_Editar)
        var etCod = findViewById<TextView>(R.id.et_CodF)
        var etNombre = findViewById<TextView>(R.id.et_NombreF)
        var etNumero = findViewById<TextView>(R.id.et_NumFac)
        var etFecha = findViewById<TextView>(R.id.et_FechaFac)
        var etBiblio = findViewById<TextView>(R.id.et_BibliFac)

        tvEditar.text = facultadRecibida!!.cod + " - " + facultadRecibida.nombre
        etCod.text = facultadRecibida!!.cod
        etNombre.text = facultadRecibida.nombre
        etNumero.text = facultadRecibida!!.num_carreras.toString()
        etFecha.text = facultadRecibida!!.fecha_fundacion
        etBiblio.text = facultadRecibida!!.biblioteca

        val btnEditarFacultad = findViewById<Button>(R.id.btn_Editar)
        btnEditarFacultad
            .setOnClickListener {
                editarFacultad(
                    etCod.text.toString(),
                    etNombre.text.toString(),
                    etNumero.text.toString().toInt(),
                    etFecha.text.toString(),
                    etBiblio.text.toString()
                )
                irActividad(actualizarEmpresa::class.java)

            }
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun editarFacultad(
        codFac: String,
        nombreFac: String,
        carreFac: Int,
        fechaFac: String,
        bibFac: String
    ) {

        //Datos a guardar
        val facultadData = Empresa(
            codFac,
            nombreFac,
            carreFac,
            fechaFac,
            bibFac
        )

        ///Referencia a firestore
        val db = Firebase.firestore
        //Coleccion donde se guardara los doc
        val facultad = db.collection("facultad")
        //Guardar documento
        facultad.document(codFac).set(facultadData)
    }
}