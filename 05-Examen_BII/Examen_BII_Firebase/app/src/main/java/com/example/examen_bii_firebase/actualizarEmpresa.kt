package com.example.examen_bii_firebase

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase

class actualizarEmpresa : AppCompatActivity() {

    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                }
            }
        }

    lateinit var adaptador: ArrayAdapter<Empresa>
    lateinit var listview: ListView

    var arreglo: ArrayList<Empresa> = ArrayList<Empresa>()

    var idItemSelecc = 0
    var idItemSeleccLv = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa)

        //Arreglo a mostrar
        consultarEmpresas()

        listview = findViewById<ListView>(R.id.lvEmpresa)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listview.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnCrearEmpresa = findViewById<Button>(R.id.btnCrearEmpresa)
        btnCrearEmpresa
            .setOnClickListener {
                insertarEmpresa(adaptador)
            }

        listview.setOnItemClickListener {parent, view, position, id ->
            idItemSeleccLv = position

            registerForContextMenu(listview)
            openContextMenu(listview)

            true

        }



    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_empresa, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        idItemSelecc = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_Editar -> {

                var  empresa = listview.getItemAtPosition(idItemSeleccLv)

                var codEmpresa = getCodF(empresa as Empresa)
                var nombreEmpresa = getNombreF(empresa as Empresa)
                var numDepartamentos = getNumDepartamentos(empresa as Empresa)
                var direccion = getDireccion(empresa as Empresa)

                abrirEditarEmpresaParametros(
                    editarEmpresa::class.java,
                    codEmpresa,
                    nombreEmpresa,
                    numDepartamentos,
                    direccion
                )

                return true

            }

            R.id.mi_Eliminar -> {

                var  empresa = listview.getItemAtPosition(idItemSeleccLv)

                var codEmpresa = getCodF(empresa as Empresa)

                eliminarEmpresa(codEmpresa)

                return true
            }


            R.id.mi_Ver ->{
                "${idItemSelecc}"

                var empresa =  listview.getItemAtPosition(idItemSeleccLv)
                var codEmpresa = getCodF(empresa as Empresa)
                var nombreEmpresa = getNombreF(empresa as Empresa)

                abrirVerEmpleadoParametros(
                    actualizarEmpresa::class.java,
                    codEmpresa,
                    nombreEmpresa
                )
                return true
            }

            else -> super.onContextItemSelected(item)

            }
        }

    fun insertarEmpresa(
        adaptador: ArrayAdapter<Empresa>
    ) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.crear_empresa, null)
        val codEmpresa = dialogLayout.findViewById<EditText>(R.id.codEmpresa)
        val nombreEmpresa = dialogLayout.findViewById<EditText>(R.id.nombreEmpresa)
        val numDepartamentos = dialogLayout.findViewById<EditText>(R.id.numDepartamentos)
        val direccion = dialogLayout.findViewById<EditText>(R.id.direccionEmpresa)

        with(builder){
            setTitle("Empresa")
            setPositiveButton("Crear"){dialog, which ->
                val empresaData = Empresa(
                    codEmpresa.text.toString(),
                    nombreEmpresa.text.toString(),
                    numDepartamentos.text.toString().toInt(),
                    direccion.text.toString()
                )

                val db = Firebase.firestore

                val empresa = db.collection("empresa")

                empresa.document(codEmpresa.text.toString()).set(empresaData)

                arreglo.clear()
                consultarEmpresas()
                adaptador.notifyDataSetChanged()

            }
            setNegativeButton("Cancelar"){dialog, which ->
                Toast.makeText(applicationContext, "Cancelado", Toast.LENGTH_SHORT).show()
            }
            setView(dialogLayout)
            show()
        }


    }

    fun consultarEmpresas(){

        val db = Firebase.firestore
        val empresa = db.collection("empresa")

        empresa.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val empresaEncontrada = Empresa("","","".toInt(),"")

                    empresaEncontrada.codEmpresa = document.data.get("codEmpresa").toString()
                    empresaEncontrada.nombre = document.data.get("nombreEmpresa").toString()
                    empresaEncontrada.numDepartamentos = document.data.get("numDepartamentos").toString().toInt()
                    empresaEncontrada.direccion = document.data.get("direccion").toString()

                    arreglo.add(empresaEncontrada)

                }

                adaptador.notifyDataSetChanged()
            }
    }

    fun eliminarEmpresa(
        codEmpresa: String
    ){
        val db = Firebase.firestore
        val empresa = db.collection("empresa")

        empresa.document(codEmpresa).delete()

        arreglo.clear()
        consultarEmpresas()
        adaptador.notifyDataSetChanged()
    }

    fun abrirEditarEmpresaParametros(
        clase: Class<*>,
        codEmpresa: String,
        nombreEmpresa: String,
        numDepartamentos: Int,
        direccion: String
    ){
        val intentExplicito = Intent(
            this,
            clase
        )

        intentExplicito.putExtra("codEmpresa", codEmpresa)
        intentExplicito.putExtra("nombreEmpresa", nombreEmpresa)
        intentExplicito.putExtra("numDepartamentos", numDepartamentos)
        intentExplicito.putExtra("direccion", direccion)

        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun abrirVerEmpleadoParametros(
        clase: Class<*>,
        codEmpresa: String,
        nombreEmpresa: String
    ){
        val intentExplicito = Intent(
            this,
            clase
        )

        intentExplicito.putExtra("codEmpresa", codEmpresa)
        intentExplicito.putExtra("nombreEmpresa", nombreEmpresa)

        contenidoIntentExplicito.launch(intentExplicito)
    }


    fun getCodF(empresa: Empresa): String{
        return ""+empresa.codEmpresa
    }

    fun getNombreF(empresa: Empresa): String{
        return ""+empresa.nombre
    }

    fun getNumDepartamentos(empresa: Empresa): Int{
        return empresa.numDepartamentos!!.toInt()
    }

    fun getDireccion(empresa: Empresa): String{
        return ""+empresa.direccion
    }


}