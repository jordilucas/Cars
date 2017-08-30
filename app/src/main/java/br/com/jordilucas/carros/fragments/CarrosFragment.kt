package br.com.jordilucas.carros.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.utils.TipoCarro
import kotlinx.android.synthetic.main.fragment_carros.*

class CarrosFragment : BaseFragment() {

    private var tipoCarro: TipoCarro = TipoCarro.classicos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tipoCarro = arguments.getSerializable("tipo") as TipoCarro

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_carros, container, false)
        val tipoString = getString(tipoCarro.string)
        val textView = view?.findViewById<TextView>(R.id.text)
        textView?.text = "Carros $tipoString"

        return view
    }
}
