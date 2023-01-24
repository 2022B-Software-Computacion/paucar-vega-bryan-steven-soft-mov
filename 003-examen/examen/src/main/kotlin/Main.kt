import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files.write
import java.nio.file.StandardOpenOption
import java.time.LocalDate
import javax.swing.JOptionPane


fun main(args: Array<String>) {

    val pathSt = "Recursos/estudiantes.txt"
    val pathFa = "Recursos/facultades.txt"

    var s = "n"
    //Menu
    val menu = "-----Empleado-----\n1) Consultar Empleado\n2) Consultar Empleado por código\n" +
            "3)Actualizar Empleado\n4) Borrar Empleado\n5) Insertar Empleado" +
            "\n-----Empresa-----\n6) Consultar Empresa\n7) Consultar Empresa por código\n8)Actualizar Empresa" +
            "\n9) Borrar Empresa\n10) Insertar Empresa\n\ns) Salir"

    do {
        var opM = JOptionPane.showInputDialog(null, menu, "Menú", -1)
        when (opM) {
            "1" -> JOptionPane.showMessageDialog(null, readFile(pathSt), "Empleado", -1)

            "2" -> {
                var opC = JOptionPane.showInputDialog("Ingrese código")
                JOptionPane.showMessageDialog(null, search(opC, pathSt), "Buscar Empleado", -1)
            }

            "3" -> {
                var opC = JOptionPane.showInputDialog("Ingrese código")
                var opA = JOptionPane.showInputDialog(
                    "--Atributos actualizables--\n" +
                            "1) sueldo\n2) edad\n\nIngrese el atributo a actualizar"
                )
                var opU = JOptionPane.showInputDialog("Ingrese el valor")

                updateStudent(opC, pathSt, opA, opU, opU)
            }

            "4" -> {
                var opC = JOptionPane.showInputDialog("Ingrese código")
                delete(opC, pathSt)
            }

            "5" -> {
                var cod = JOptionPane.showInputDialog("Ingrese código")
                var nombre = JOptionPane.showInputDialog("Ingrese nombre")
                var apellido = JOptionPane.showInputDialog("Ingrese apellido")
                var sueldo = JOptionPane.showInputDialog("Ingrese sueldo")
                var edad = JOptionPane.showInputDialog("Ingrese edad")
                var nomEmpresa = JOptionPane.showInputDialog("Ingrese nombre Empresa")
                var cargo = JOptionPane.showInputDialog("Ingrese cargo")

                createStudent(cod, nombre, apellido, sueldo.toDouble(), edad.toInt(), nomEmpresa, cargo, pathSt)
            }

            "6" -> JOptionPane.showMessageDialog(null, readFile(pathFa), "Empresa", -1)

            "7" -> {
                var opC = JOptionPane.showInputDialog("Ingrese código")
                JOptionPane.showMessageDialog(null, search(opC, pathFa), "Buscar Empresa", -1)
            }

            "8" -> {
                var opC = JOptionPane.showInputDialog("Ingrese código")
                var opA = JOptionPane.showInputDialog(
                    "--Atributos actualizables--\n" +
                            "1) pagaImpuestos\n\nIngrese el atributo a actualizar"
                )
                var opU = JOptionPane.showInputDialog("Ingrese el valor")

                updateFa(opC, pathFa, opA, opU)
            }

            "9" -> {
                var opC = JOptionPane.showInputDialog("Ingrese código")
                delete(opC, pathFa)
            }

            "10" -> {
                var cod = JOptionPane.showInputDialog("Ingrese código")
                var tipo = JOptionPane.showInputDialog("Ingrese tipo")
                var nombre = JOptionPane.showInputDialog("Ingrese nombre")
                var date = JOptionPane.showInputDialog("Ingrese fecha de fundación")
                var impuestos = JOptionPane.showInputDialog("Paga impuestos disponible (true, false)?")

                createFa(cod, tipo, nombre, LocalDate.parse(date), impuestos.toBoolean(), pathFa)
            }

            "s" -> s = "s"

            else -> JOptionPane.showMessageDialog(null, "No reconocido")
        }
    } while (s.equals("n"))
}

fun readFile(path: String): String {
    val miArchivo = File(path)
    val lineas = miArchivo.readLines()
    var res = ""
    lineas.forEach {
        res = res + it + "\n"
    }
    return res
}

fun search(cod: String, path: String): String {
    val miArchivo = File(path)
    val lineas = miArchivo.readLines()
    var res = "No se encontró ningún registro"
    var aux = ""
    lineas.forEach {
        if (it.contains(cod)) {
            aux = aux + it + "\n"
            res = aux
        }

    }
    return res
}

fun updateStudent(cod: String, path: String, op: String, sueldo: String = "", edad: String = "") {

    val miArchivo = File(path)
    val lineas = miArchivo.readLines()
    var input: String = "";

    lineas.forEach {
        if (it.contains(cod)) {

            when (op) {
                "1" -> {
                    var sueldo_aux = it.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[3]
                    input += it.replace(sueldo_aux, sueldo)
                }
                "2" -> {
                    var edad_aux = it.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[4]
                    input += it.replace(edad_aux, edad)
                }
            }

            input += "\n"

        } else {
            input += it + "\r\n"

        }

    }

    val fileOut = FileOutputStream(path)
    fileOut.write(input.toByteArray())
    fileOut.close()

}

fun updateFa(cod: String, path: String, op: String, impuestos: String = "") {

    val miArchivo = File(path)
    val lineas = miArchivo.readLines()
    var input: String = "";

    lineas.forEach {
        if (it.contains(cod)) {

            when (op) {
                "1" -> {
                    var impuestos_aux = it.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[4]
                    input += it.replace(impuestos_aux, impuestos)
                }
            }

            input += "\n"

        } else {
            input += it + "\r\n"

        }

    }

    val fileOut = FileOutputStream(path)
    fileOut.write(input.toByteArray())
    fileOut.close()

}

fun delete(cod: String, path: String) {

    val miArchivo = File(path)
    val lineas = miArchivo.readLines()
    var input: String = "";
    lineas.forEach {
        if (!it.contains(cod)) {
            input += it + "\r\n"

        }

    }
    val fileOut = FileOutputStream(path)
    fileOut.write(input.toByteArray())
    fileOut.close()
}

fun createStudent(
    id: String,
    nombre: String,
    apellido: String,
    sueldo: Double,
    edad: Int,
    cod: String,
    cargo: String,
    path: String
) {
    val outString = "\n${id} ${nombre} ${apellido} ${sueldo} ${edad} ${cod} ${cargo}"
    val archivo = File(path)
    write(archivo.toPath(), outString.toByteArray(), StandardOpenOption.APPEND)
}

fun createFa(
    cod: String,
    tipo: String,
    nombre: String,
    fecha: LocalDate,
    impuestos: Boolean,
    path: String) {
    val outString = "\n${cod} ${tipo} ${nombre} ${fecha} ${impuestos} ${cod}"
    val archivo = File(path)
    write(archivo.toPath(), outString.toByteArray(), StandardOpenOption.APPEND)
}