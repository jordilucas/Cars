package br.com.jordilucas.carros.extensions

import android.app.Activity
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.widget.Toast

/**
 * Created by jordi on 29/08/17.
 */

    //FindViewById + setOnClickListener
    fun AppCompatActivity.onClick(@IdRes viewId: Int, onClick:(V: android.view.View?) -> Unit){
        val view = findViewById<View>(viewId)
        view.setOnClickListener{onClick(it)}
    }

    //Show Toast
    fun Activity.toast(message: CharSequence, length: Int = Toast.LENGTH_LONG) =
            Toast.makeText(this, message, length).show()

    //Show Toast
    fun Activity.toast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) =
            Toast.makeText(this, message, length).show()

    //Configura toolbar
    fun AppCompatActivity.setupToolbar(@IdRes id: Int, title: String? = null,
                                       upNavigation: Boolean = false) : ActionBar {
        val toolbar = findViewById<Toolbar>(id)
        setSupportActionBar(toolbar)
        if(title != null){
            supportActionBar?.title = title
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)
        return supportActionBar!!
    }

    fun AppCompatActivity.addFragment(@IdRes layoutId: Int, fragment: Fragment){
        fragment.arguments = intent.extras
        val ft = supportFragmentManager.beginTransaction()
        ft.add(layoutId, fragment)
        ft.commit()
    }

