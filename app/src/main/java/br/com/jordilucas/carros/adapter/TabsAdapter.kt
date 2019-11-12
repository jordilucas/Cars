package br.com.jordilucas.carros.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.jordilucas.carros.fragments.CarrosFragment
import br.com.jordilucas.carros.domain.TipoCarro
import br.com.jordilucas.carros.fragments.FavoritosFragment

/**
 * Created by jordi on 19/09/17.
 */
class TabsAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getCount():Int = 4

    fun getTipoCarro(position: Int) = when(position){
        0 -> TipoCarro.classicos
        1 -> TipoCarro.esportivos
        2 -> TipoCarro.luxo
        else -> TipoCarro.favoritos
    }

    override fun getPageTitle(position: Int): CharSequence {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)
    }

    override fun getItem(position: Int): Fragment {
        if(position == 3){
            return FavoritosFragment()
        }
        val tipo = getTipoCarro(position)
        val f: Fragment = CarrosFragment()
        f.arguments = Bundle()
        f.arguments.putSerializable("tipo", tipo)
        return f
    }


}