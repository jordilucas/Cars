package br.com.jordilucas.carros.domain

import android.content.Context
import android.util.Log
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.extensions.fromJson
import br.com.jordilucas.carros.utils.TipoCarro
import org.json.JSONArray
import java.net.URL

/**
 * Created by jordi on 31/08/17.
 */

object CarroService {

    private val TAG = "livro"

    fun getCarros(tipo:TipoCarro):List<Carro>{

        val url = "http://livrowebservices.com.br/rest/carros/tipo/${tipo.name}"
        Log.d(TAG, url)
        val json = URL(url).readText()
        val carros = fromJson<List<Carro>>(json)
        return carros

        /*val raw = getArquivoRaw(tipo)
        val resources = context.resources
        val inputStream = resources.openRawResource(raw)
        inputStream.bufferedReader().use{
            val json = it.readText()
            val carros = fromJson<List<Carro>>(json)
            return carros
        }*/
    }



    fun getArquivoRaw(tipo: TipoCarro) = when(tipo){
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }

}

