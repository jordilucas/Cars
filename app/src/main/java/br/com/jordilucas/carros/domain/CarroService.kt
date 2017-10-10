package br.com.jordilucas.carros.domain

import android.util.Base64
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.domain.dao.DatabaseManager
import br.com.jordilucas.carros.domain.retrofit.CarrosRest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

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

    fun postFoto(file: File): Response? {

        // Converte para Base64
        val bytes = file.readBytes()
        val base64 = Base64.encodeToString(bytes, Base64.NO_WRAP)

        val call = service.postFoto(file.name,base64)
        val response = call.execute().body()
        return response
    }



    fun getArquivoRaw(tipo: TipoCarro) = when(tipo){
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }

}

