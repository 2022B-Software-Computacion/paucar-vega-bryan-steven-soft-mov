package com.example.examen_bii_firebase

import android.os.Parcel
import android.os.Parcelable

data class Empleado (
    var id: Int?,
    var nombre: String?,
    var apellido: String?,
    var sueldo: String?,
    var edad: Int?,
    var codEmpresa: String?
): Parcelable {

    override fun toString(): String {
        return "${id} - ${nombre} - ${apellido}"
    }

    constructor(parcel: Parcel): this (
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
            )
    {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id!!)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(sueldo)
        parcel.writeInt(edad!!)
        parcel.writeString(codEmpresa)
    }

    companion object CREATOR : Parcelable.Creator<Empleado> {
        override fun createFromParcel(parcel: Parcel): Empleado {
            return Empleado(parcel)
        }

        override fun newArray(size: Int): Array<Empleado?> {
            return arrayOfNulls(size)
        }
    }


}

