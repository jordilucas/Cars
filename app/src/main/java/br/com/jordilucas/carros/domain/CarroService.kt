package br.com.jordilucas.carros.domain

import android.content.Context
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.extensions.fromJson
import br.com.jordilucas.carros.utils.TipoCarro
import org.json.JSONArray

/**
 * Created by jordi on 31/08/17.
 */

object CarroService {

    private val TAG = "livro"

    fun getCarros(context: Context, tipo:TipoCarro):List<Carro>{

        val raw = getArquivoRaw(tipo)
        val resources = context.resources
        val inputStream = resources.openRawResource(raw)
        inputStream.bufferedReader().use{
            val json = it.readText()
            val carros = fromJson<List<Carro>>(json)
            return carros
        }

        /*val tipoString = context.getString(tipo.string)
        val carros = mutableListOf<Carro>()
        for(i in 1 .. 20){
            val c = Carro()
            c.nome = "Carro $tipoString: $i"
            c.desc = "Desc " + i
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png"
            carros.add(c)
        }

        return carros*/
    }



    fun getArquivoRaw(tipo: TipoCarro) = when(tipo){
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }

}

