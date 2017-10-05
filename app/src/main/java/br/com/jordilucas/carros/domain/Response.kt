package br.com.jordilucas.carros.domain

/**
 * Created by jordi on 05/10/17.
 */
data class Response (val id:Long, val status:String, val msg:String, val url:String){
    fun isOK() = "OK".equals(status, ignoreCase = true)
}