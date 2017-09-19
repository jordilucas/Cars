package br.com.jordilucas.carros.domain

import java.io.Serializable

/**
 * Created by jordi on 31/08/17.
 */
class Carro : Serializable {

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

}