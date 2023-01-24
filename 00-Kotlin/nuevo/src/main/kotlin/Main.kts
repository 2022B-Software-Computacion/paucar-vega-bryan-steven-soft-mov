import java.util.*

// Main.kt
fun main() {
    println("Hola mundo")
    // Tipos de variables

    // INMUTABLES (NO Re Asignar) =
    val inmutable: String = "Adrian";
    //inmutable = "Vicente"; // No se puede re asignar

    // MUTABLES (Re Asignar) =
    var mutable: String = "Vicente"
    mutable = "Adrian"

    // val > var

    //Sintaxis Duck typing
    val ejemploVariable = "Ejemplo"
    val edadEjemplo: Int = 12
    ejemploVariable.trim()

    // Variables primitivas
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true
    // Clases JAVA
    val fechaNacimiento: Date = Date()

    if (true) {
    } else {
    }
    // switch no existe
    val estadoCivilWhen = "S"
    when (estadoCivilWhen) {
        ("S") -> {
            println("acercarse")
        }
        "C" -> {
            println("alejarse")
        }
        "UN" -> println("hablar")
        else -> println("No reconnocido")
    }
    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"

    val sumaUno = Suma(1,1);
    val sumaDos = Suma(null,1);
    val sumaTres = Suma(1,null);
    val sumaCuatro = Suma(null,null);
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    Suma.pi
    Suma.elevarAlCuadrado(2)
    println(Suma.historialSumas)


    // ARREGLOS

    // Tipos de Arreglos

    // Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

    // Arreglo Dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)
    // OPERADORES -> Sirven para los arreglos estáticos y dinámicos

    // FOR EACH -> Unit
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach {
                valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }

    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }
//        .map { valorActual: Int ->
//            return@map valorActual + 15
//        }

    println(respuestaMapDos)

    // Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5// Expresion Condicion
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    // OR ->  ANY (Alguno cumple?)
    // AND ->  ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // false

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Logica negocio
        }
    println(respuestaReduce) // 78


}


// void imprimirNombre(String nombre){}
// Unit == void
fun imprimirNombre(nombre: String): Unit {
    println("Nombre : ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, // Requerido
    tasa: Double = 12.00, // Opcional (Defecto)
    bonoEspecial: Double? = null, // Opcional (Null) -> nullable
): Double {
    // String -> String?
    // Int -> Int?
    // Date -> Date?
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ) { // Bloque codigo constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializado")
    }
}

abstract class Numeros( // Constructor Primario
//     uno: Int, // Parametro
//    public var uno: Int, // Propiedad de la clase publica
//    var uno: Int, // Propiedad de la clase publica
    protected val numeroUno: Int, // Propiedad de la clase protected
    protected val numeroDos: Int // Propiedad de la clase protected
) {
//    protected val numeroUno: Int
//    var cedula: String = "";
//    public var cedula: String = "";

    init { // Bloque codigo constructor PRIMARIO
//        this.numeroUno = uno
        this.numeroUno
        numeroUno
        this.numeroDos
        numeroDos
        println("Inicializado")
    }
}

class Suma( // Constructor Primario Suma
    uno: Int, // Parametro
    dos: Int // Parametro
) : Numeros(uno, dos) {
    init { // Bloque constructor primario
        this.numeroUno
        this.numeroDos
    }

    constructor(//  Segundo constructor
        uno: Int?, // parametros
        dos: Int // parametros
    ) : this(  // llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    ) {
    }

    constructor(//  tercer constructor
        uno: Int, // parametros
        dos: Int? // parametros
    ) : this(  // llamada constructor primario
        uno,
        if (dos == null) 0 else uno
    )
    constructor(//  cuarto constructor
        uno: Int?, // parametros
        dos: Int? // parametros
    ) : this(  // llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else uno
    )
    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    companion object { // Atributos y Metodos "Compartidos"
        // entre las instancias
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}
