package br.com.jordilucas.carros.fragments

import br.com.jordilucas.carros.activity.CarroActivity
import br.com.jordilucas.carros.adapter.CarroAdapter
import br.com.jordilucas.carros.domain.Carro
import br.com.jordilucas.carros.domain.FavoritosService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.startActivity

/**
 * Created by jordi on 09/10/17.
 */
class FavoritosFragment : CarrosFragment(){
    override fun taskCarros(){
        doAsync {
            carros = FavoritosService.getCarros()
            uiThread {
                recyclerView!!.adapter = CarroAdapter(carros){onClickCarro(it)}
            }
        }
    }

    override fun onClickCarro(carro: Carro) {
        activity.startActivity<CarroActivity>("carro" to carro)
    }

}