package com.example.pbapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class ESqliteHelperEntrenador(
    context: Context?, //This

) : SQLiteOpenHelper(
    context,
    "moviles", //Nombre de la BDD
    null,
    1
) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scripSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scripSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun crearEntrenador(
        nombre : String,
        descipcion : String
    ): Boolean{

        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descipcion)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "ENTRENADOR", //Nombre de la BD
                null,
                valoresAGuardar //Valores
            )
        basedatosEscritura.close()
        return resultadoGuardar.toInt() != -1
    }
}
