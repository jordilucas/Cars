package br.com.jordilucas.carros.activity


import android.os.Bundle
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.domain.Carro
import br.com.jordilucas.carros.extensions.loadUrl
import br.com.jordilucas.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*


class CarroActivity : BaseActivity() {

    val carro:Carro by lazy {intent.getParcelableExtra<Carro>("carro")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        setupToolbar(R.id.toolbar, carro.nome, true)

        initViews()
    }

    fun initViews(){
        tDesc.text = carro.desc
        appBarImg.loadUrl(carro.urlFoto)
    }
}
