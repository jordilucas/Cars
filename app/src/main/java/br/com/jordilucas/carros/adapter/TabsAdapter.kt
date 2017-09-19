package br.com.jordilucas.carros.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.jordilucas.carros.fragments.CarrosFragment
import br.com.jordilucas.carros.utils.TipoCarro

/**
 * Created by jordi on 19/09/17.
 */
class TabsAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getCount():Int = 3

    fun getTipoCarro(position: Int) = when(position){
        0 -> TipoCarro.classicos
        1 -> TipoCarro.esportivos
        else -> TipoCarro.luxo
    }

    override fun getPageTitle(position: Int): CharSequence {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)
    }

    override fun getItem(position: Int): Fragment {
        val tipo = getTipoCarro(position)
        val f: Fragment = CarrosFragment()
        f.arguments = Bundle()
        f.arguments.putSerializable("tipo", tipo)
        return f
    }


}