package br.com.jordilucas.carros.fragments

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.adapter.CarroAdapter
import br.com.jordilucas.carros.domain.Carro
import br.com.jordilucas.carros.domain.CarroService
import br.com.jordilucas.carros.extensions.toast
import br.com.jordilucas.carros.utils.TipoCarro
import kotlinx.android.synthetic.main.fragment_carros.*

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
        this.carros = CarroService.getCarros(context, tipoCarro)
        recyclerView?.adapter = CarroAdapter(carros){
            onClickCarro(it)
        }
    }

    fun onClickCarro(carro: Carro){
        toast("Clicou no carro ${carro.nome}")
    }

}
