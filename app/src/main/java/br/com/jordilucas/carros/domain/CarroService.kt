package br.com.jordilucas.carros.domain

import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.domain.dao.DatabaseManager
import br.com.jordilucas.carros.domain.retrofit.CarrosRest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jordi on 31/08/17.
 */

object CarroService {

    private val TAG = "livro"
    private val BASE_URL = "http://livrowebservices.com.br/rest/carros/"

    private var service: CarrosRest

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(CarrosRest::class.java)
    }

    fun getCarros(tipo: TipoCarro): List<Carro>? {
        val call = service.getCarros(tipo.name)
        val carros = call.execute().body()
        return carros
    }

    fun save(carro:Carro): Response? {
        val call = service.save(carro)
        val response = call.execute().body()
        return response
    }

    fun delete(carro: Carro): Response?{
        val call = service.delete(carro.id)
        val response = call.execute().body()
        if(response!!.isOK()){
            val dao = DatabaseManager.getCarroDAO()
            dao.delete(carro)
        }
        return response
    }

   /* fun getCarros(tipo:TipoCarro):List<Carro>{

        val url = "http://livrowebservices.com.br/rest/carros/tipo/${tipo.name}"
        Log.d(TAG, url)
        val json = URL(url).readText()
        val carros = fromJson<List<Carro>>(json)
        return carros

        *//*val raw = getArquivoRaw(tipo)
        val resources = context.resources
        val inputStream = resources.openRawResource(raw)
        inputStream.bufferedReader().use{
            val json = it.readText()
            val carros = fromJson<List<Carro>>(json)
            return carros
        }*//*
    }*/



    fun getArquivoRaw(tipo: TipoCarro) = when(tipo){
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }

}

