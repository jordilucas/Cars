package br.com.jordilucas.carros.activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by jordi on 29/08/17.
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(){
    protected val context: Context get() = this
}