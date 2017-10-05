package br.com.jordilucas.carros.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by jordi on 31/08/17.
 */
class Carro : Parcelable {




    var id:Long = 0
    var tipo = ""
    var nome = ""
    var desc = ""
    var urlFoto = ""
    var urlInfo = ""
    var urlVideo = ""
    var latitude = ""
    var longitude = ""

    override fun toString(): String {
        return "Carro(nome=$nome)"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {

        dest.writeLong(id)
        dest.writeString(this.tipo)
        dest.writeString(this.nome)
        dest.writeString(this.desc)
        dest.writeString(this.urlFoto)
        dest.writeString(this.urlInfo)
        dest.writeString(this.urlVideo)
        dest.writeString(this.latitude)
        dest.writeString(this.longitude)

    }

    fun readFromParcel(parcel: Parcel) {
        id = parcel.readLong()
        tipo = parcel.readString()
        nome = parcel.readString()
        desc = parcel.readString()
        urlFoto = parcel.readString()
        urlInfo = parcel.readString()
        urlVideo = parcel.readString()
        latitude = parcel.readString()
        longitude = parcel.readString()
    }


    companion object {
        private val serialVersionUID = 6601006766832473959L

        @JvmField val CREATOR: Parcelable.Creator<Carro> =
                object : Parcelable.Creator<Carro> {
                    override fun createFromParcel(parcel: Parcel): Carro {

                        val c = Carro()
                        c.readFromParcel(parcel)

                        return c
                    }
                    override fun newArray(size: Int): Array<Carro?> {
                        return arrayOfNulls(size)
                    }
                }

    }

}