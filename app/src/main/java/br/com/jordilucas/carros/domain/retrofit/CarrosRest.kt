package br.com.jordilucas.carros.domain.retrofit

import br.com.jordilucas.carros.domain.Carro
import br.com.jordilucas.carros.domain.Response
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by jordi on 05/10/17.
 */
interface CarrosRest{
    @GET("tipo/{tipo}")
    fun getCarros(@Path("tipo") tipo: String): Call<List<Carro>>
    @POST("./")
    fun save(@Body carro: Carro): Call<Response>
    @DELETE("{id}")
    fun delete(@Path("id") id: Long): Call<Response>
    @FormUrlEncoded
    @POST("postFotoBase64")
    fun postFoto(@Field("fileName") fileName:String, @Field("base64") base64:String): Call<Response>
}