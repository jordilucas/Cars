package br.com.jordilucas.carros.extensions

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

/**
 * Created by jordi on 29/08/17.
 */
class Activity_Extensions {

    //FindViewById + setOnClickListener
    fun AppCompatActivity.onClick(@IdRes viewId: Int, onClick:(V: android.view.View?) -> Unit){
        val view = findViewById<View>(viewId)
        view.setOnClickListener{onClick(it)}
    }

    //Show Toast
    fun Activity.toast(message: CharSequence, length: Int = Toast.LENGTH_LONG) =
            Toast.makeText(this, message, length).show()



}