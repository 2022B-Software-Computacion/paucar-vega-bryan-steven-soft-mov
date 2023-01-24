package com.example.pbapplication

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< HEAD
=======
import android.app.Activity
>>>>>>> Stashed changes
=======
import android.app.Activity
>>>>>>> Stashed changes
=======
import android.app.Activity
>>>>>>> Stashed changes
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.util.Log
import android.view.View
import android.widget.Button
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
>>>>>>> main
=======
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
>>>>>>> Stashed changes
=======
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
>>>>>>> Stashed changes
=======
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
>>>>>>> Stashed changes

class MainActivity : AppCompatActivity() {


    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK){
                if (result.data != null){
                    val data = result.data
                    Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                }

            }
        }

    val contenidoIntentImplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if(result.resultCode == RESULT_OK){
            if (result.data!!.data != null){

                val uri:Uri = result.data!!.data!!
                val cursor = contentResolver.query(
                    uri,
                    null,
                    null,
                    null,
                    null,
                    null
                )
                cursor?.moveToFirst()
                val indiceTelefono = cursor?.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )
                val telefono = cursor?.getString(
                    indiceTelefono!!
                )
                cursor?.close()
                Log.i("intent-epn", "Telefono ${telefono}")
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< HEAD
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
<<<<<<< HEAD
=======
<<<<<<< Updated upstream
            irActividad(ACicloVida::class.java)}
=======
>>>>>>> main
            irActividad(ACicloVida::class.java)
            }

        val botonlistView = findViewById<Button>(R.id.btn_ir_list_view)
        botonlistView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }
<<<<<<< HEAD
=======

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                contenidoIntentImplicito.launch(intentConRespuesta)
            }

        val botonIntentExplicito = findViewById<Button>(R.id.btn_ir_intent_explicito)
        botonIntentExplicito
            .setOnClickListener{
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }
    }

    fun abrirActividadConParametros(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(this, clase)
        //Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Bryan")
        intentExplicito.putExtra("apellido", "Paucar")
        intentExplicito.putExtra("edad", 23)
        intentExplicito.putExtra(
            "entrenadorPrincipal",
            BEntrenador(1,"Brya", "Estudiante")
        )
        contenidoIntentExplicito.launch(intentExplicito);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
>>>>>>> main
    }

    fun irActividad(
        clase: Class <*>
    ){
        val intent =  Intent(this, clase)
        startActivity(intent)
=======
>>>>>>> main
    }
}