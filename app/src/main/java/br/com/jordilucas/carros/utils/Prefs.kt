package br.com.jordilucas.carros.utils

import android.content.SharedPreferences
import br.com.jordilucas.carros.CarrosAplication

/**
 * Created by jordisantos on 09/10/17.
 */
object Prefs {
    val PREF_ID = "carros"
    private fun prefs(): SharedPreferences{
        val context = CarrosAplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID,0)
    }

    fun setInt(flag: String, valor: Int){
        val pref = prefs()
        val editor = pref.edit()
        editor.putInt(flag, valor)
        editor.apply()
    }

    fun getInt(flag: String): Int{
        val pref = prefs()
        val i = pref.getInt(flag,0)
        return i
    }

    fun setInstring(flag: String, valor: String){
        val pref = prefs()
        val editor = pref.edit()
        editor.putString(flag, valor)
        editor.apply()
    }

    fun getString(flag: String):String{
        val pref = prefs()
        val s = pref.getString(flag, "")
        return s
    }

}