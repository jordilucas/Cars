package br.com.jordilucas.carros.extensions

import android.widget.TextView

/**
 * Created by jordi on 05/10/17.
 */
var TextView.string: String
get() = text.toString()
set(value){text = value}
fun TextView.isEmpty() = text.trim().isEmpty()