package br.com.jordilucas.carros.activity


import android.os.Bundle
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.domain.Carro
import br.com.jordilucas.carros.extensions.loadUrl
import br.com.jordilucas.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro_contents.*

class CarroActivity : BaseActivity() {

    var carro: Carro? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)

        carro = intent.getSerializableExtra("carro") as Carro
        setupToolbar(R.id.toolbar, carro?.nome, true)

        initViews()

    }

    fun initViews(){
        tDesc.text = carro?.desc
        img.loadUrl(carro?.urlFoto)
    }

}
