package br.com.jordilucas.carros.activity

import android.os.Bundle
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.extensions.addFragment
import br.com.jordilucas.carros.extensions.setupToolbar
import br.com.jordilucas.carros.fragments.CarrosFragment
import br.com.jordilucas.carros.utils.TipoCarro

class CarrosActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carros)
        val tipo = intent.getSerializableExtra("tipo") as TipoCarro
        val title = getString(tipo.string)

        setupToolbar(R.id.toolbar, title, true)

        if(savedInstanceState  == null){
            addFragment(R.id.container, CarrosFragment())
        }

    }
}
