package com.app4fun.carros.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Carro : Parcelable {

    var id: Long = 0
    var tipo = ""
    var nome = ""
    var desc = ""
    @SerializedName("url_foto")
    var urlFoto = ""
    @SerializedName("url_info")
    var urlInfo = ""
    @SerializedName("url_video")
    var urlVideo = ""
    var latitude = ""
    var longitude = ""

    override fun toString(): String {
        return "Carro (nome = '$nome')"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        //Escreve os dados a serem Serializado
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

    fun readFromParcel(parcel: Parcel){
        //Lê os dados na mesma ordem que foram escritos
        this.id = parcel.readLong()
        this.tipo = parcel.readString()
        this.nome = parcel.readString()
        this.desc = parcel.readString()
        this.urlFoto = parcel.readString()
        this.urlInfo = parcel.readString()
        this.urlVideo = parcel.readString()
        this.latitude = parcel.readString()
        this.longitude = parcel.readString()
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Carro> =
                object : Parcelable.Creator<Carro>{
                    override fun createFromParcel(p: Parcel): Carro {
                        //Cria o objeto carro com um Parcel
                        val c = Carro()
                        c.readFromParcel(p)
                        return c
                    }

                    override fun newArray(size: Int): Array<Carro?> {
                        //Retorna um array vazio
                        return arrayOfNulls(size)
                    }
                }
    }
}