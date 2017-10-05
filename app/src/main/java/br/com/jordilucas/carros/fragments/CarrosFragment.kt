package br.com.jordilucas.carros.fragments

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.startActivity
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.activity.CarroActivity
import br.com.jordilucas.carros.adapter.CarroAdapter
import br.com.jordilucas.carros.domain.Carro
import br.com.jordilucas.carros.domain.CarroService
import br.com.jordilucas.carros.utils.TipoCarro
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CarrosFragment : BaseFragment() {

    private var tipoCarro: TipoCarro = TipoCarro.classicos
    private var carros = listOf<Carro>()
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tipoCarro = arguments.getSerializable("tipo") as TipoCarro

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_carros, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        taskCarros()
    }

    fun taskCarros(){

        doAsync {
            carros = CarroService.getCarros(tipoCarro)
            uiThread {
                recyclerView?.adapter = CarroAdapter(carros){
                    onClickCarro(it)
                }
            }
        }
    }

    fun onClickCarro(carro: Carro){
        activity.startActivity<CarroActivity>("carro" to carro)
    }

}
