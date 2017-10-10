package br.com.jordilucas.carros.domain

import br.com.jordilucas.carros.R

/**
 * Created by jordi on 30/08/17.
 */
enum class TipoCarro(val string: Int){
    classicos(R.string.classicos),
    esportivos(R.string.esportivos),
    luxo(R.string.luxo),
    favoritos(R.string.favoritos)
}