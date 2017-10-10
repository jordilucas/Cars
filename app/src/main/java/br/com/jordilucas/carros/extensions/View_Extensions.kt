package br.com.jordilucas.carros.extensions

/**
 * Created by jordi on 10/10/17.
 */
fun android.view.View.onClick(l: (v: android.view.View?) -> Unit){
    setOnClickListener(l)
}