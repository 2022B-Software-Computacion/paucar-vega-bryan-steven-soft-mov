package com.example.examen_bii_firebase

import android.os.Parcelable

class Empresa (
    var codEmpresa: String?,
    var nombre: String?,
    var numDepartamentos: Int?,
    var direccion: String?,

    ): Parcelable{

        constructor(parcel: android.os.Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: android.os.Parcel, flags: Int) {
        parcel.writeString(codEmpresa)
        parcel.writeString(nombre)
        parcel.writeInt(numDepartamentos!!)
        parcel.writeString(direccion)
    }

    override fun toString(): String {
        return "${codEmpresa} - ${nombre}"
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Empresa> {
        override fun createFromParcel(parcel: android.os.Parcel): Empresa {
            return Empresa(parcel)
        }

        override fun newArray(size: Int): Array<Empresa?> {
            return arrayOfNulls(size)
        }
    }

}