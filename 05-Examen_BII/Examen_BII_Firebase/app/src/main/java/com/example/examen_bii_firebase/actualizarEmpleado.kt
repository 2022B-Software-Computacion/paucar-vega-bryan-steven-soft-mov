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


class actualizarEmpleado : AppCompatActivity(){

    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                }
            }
        }

    lateinit var adaptador: ArrayAdapter<Empleado>
    lateinit var listview: ListView

    var arreglo: ArrayList<Empleado> = ArrayList<Empleado>()

    var idItemSelecc = 0
    var idItemSeleccLv = 0

    var codFacEmp: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleado)

        val verEmpleado = intent.getParcelableExtra<Empresa>("verEmpleado")
        val tvEmpleado = findViewById<TextView>(R.id.tvEmpleado)

        codFacEmp = verEmpleado!!.codEmpresa!!

        tvEmpleado.text = codFacEmp + " - " + verEmpleado!!.nombre

        //Arreglo para mostrar los empleados

        consultarEmpleados(codFacEmp)

        listview = findViewById<ListView>(R.id.lvEmpleado)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listview.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnCrearEmpleado = findViewById<Button>(R.id.btnCrearEmpleado)
        btnCrearEmpleado
            .setOnClickListener {
                insertarEmpleado(codFacEmp)
            }

        listview.setOnItemClickListener { parent, view, position, id ->
            idItemSeleccLv = position
            registerForContextMenu(listview)
            openContextMenu(listview)
            true
        }
    }


        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?)
        {
            super.onCreateContextMenu(menu, v, menuInfo)

            val infalter = menuInflater
            infalter.inflate(R.menu.menu_empleado, menu)

            val info = menuInfo as AdapterView.AdapterContextMenuInfo
            val id = info.position

            idItemSelecc = id
        }

        override fun onContextItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.mi_EditarEmp -> {

                    var empleado =  listview.getItemAtPosition(idItemSeleccLv)

                    var id = getIDE(empleado as Empleado)
                    var nombre = getNombreE(empleado as Empleado)
                    var apellido = getApellidoE(empleado as Empleado)
                    var sueldo = getSueldoE(empleado as Empleado)
                    var edad = getEdadE(empleado as Empleado)
                    var codEmpresa = getCodEmpresaE(empleado as Empleado)

                    abrirEditarEmpleadoParametros(
                        editarEmpleado::class.java,
                        id,
                        nombre,
                        apellido,
                        sueldo,
                        edad,
                        codEmpresa

                    )

                    return true
                }

                R.id.mi_EliminarEmp -> {
                    "${idItemSelecc}"

                    var empleado =  listview.getItemAtPosition(idItemSeleccLv)
                    var idEmp = getIDE(empleado as Empleado)

                    eliminarEmpleado(idEmp,codFacEmp)

                    return true
                }

                else
                -> super.onContextItemSelected(item)
            }
        }

    fun insertarEmpleado(
        codi: String
    ){

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.crear_empleado, null)
        val cod = codi
        val nombreEmp = dialogLayout.findViewById<EditText>(R.id.nombreEmp)
        val apellidoEmp = dialogLayout.findViewById<EditText>(R.id.apellidoEmp)
        val sueldoEmp = dialogLayout.findViewById<EditText>(R.id.sueldoEmp)
        val edadEmp = dialogLayout.findViewById<EditText>(R.id.edadEmp)
        val idEmpleado = dialogLayout.findViewById<TextView>(R.id.idEmpleado)

        with(builder) {
            setTitle("Empleado")
            setPositiveButton("OK"){
                dialog, wich ->

                val empleadoData = Empleado(
                    idEmpleado.text.toString().toInt(),
                    nombreEmp.text.toString(),
                    apellidoEmp.text.toString(),
                    sueldoEmp.text.toString(),
                    edadEmp.text.toString().toInt(),
                    cod
                )

                val db = Firebase.firestore

                val empleado = db.collection("empleado")
                empleado.document(idEmpleado.text.toString()).set(empleadoData)

                arreglo.clear()
                consultarEmpleados(cod)
                adaptador.notifyDataSetChanged()

            }

            setNegativeButton("Cancelar"){
                dialog, wich ->
            }

            setView(dialogLayout)
            show()


        }


    }

    fun consultarEmpleados(cod: String){
        val db = Firebase.firestore
        val empleado = db.collection("empleado")
        empleado.whereEqualTo("codEmpresa", cod)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var empleadoEncontrado = Empleado(0, "", "", "", 0, "")

                    empleadoEncontrado.id = document.data.get("id").toString().toInt()
                    empleadoEncontrado.nombre = document.data.get("nombre").toString()
                    empleadoEncontrado.apellido = document.data.get("apellido").toString()
                    empleadoEncontrado.sueldo = document.data.get("sueldo").toString()
                    empleadoEncontrado.edad = document.data.get("edad").toString().toInt()
                    empleadoEncontrado.codEmpresa = cod

                    arreglo.add(
                        empleadoEncontrado
                    )
                }

                adaptador.notifyDataSetChanged()
            }
    }

    fun eliminarEmpleado(
        id: Int,
        cod: String
    ){
        val db = Firebase.firestore
        val empleado = db.collection("empleado")
        empleado.document(id.toString()).delete()

        arreglo.clear()
        consultarEmpleados(cod)
        adaptador.notifyDataSetChanged()
    }

    fun abrirEditarEmpleadoParametros(
        clase: Class<*>,
        id: Int,
        nombre: String,
        apellido: String,
        sueldo: String,
        edad: Int,
        codEmpresa: String
    ){
        val intentExplicito = Intent(
            this,
            clase
        )

        intentExplicito.putExtra("editarEmpleado", Empleado(id, nombre, apellido, sueldo, edad, codEmpresa))

        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun getIDE(empleado: Empleado): Int{
        return empleado.id!!
    }

    fun getNombreE(empleado: Empleado): String{
        return empleado.nombre!!
    }

    fun getApellidoE(empleado: Empleado): String{
        return empleado.apellido!!
    }

    fun getSueldoE(empleado: Empleado): String{
        return empleado.sueldo!!
    }

    fun getEdadE(empleado: Empleado): Int{
        return empleado.edad!!
    }

    fun getCodEmpresaE(empleado: Empleado): String{
        return empleado.codEmpresa!!
    }


}