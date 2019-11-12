package br.com.jordilucas.carros.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.widget.Toast

/**
 * Created by jordi on 31/08/17.
 */
fun Fragment.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(activity, message, length).show()

fun Fragment.toast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(activity, message, length).show()