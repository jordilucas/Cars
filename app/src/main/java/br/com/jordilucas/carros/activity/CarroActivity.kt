package br.com.jordilucas.carros.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.domain.Carro
import br.com.jordilucas.carros.domain.CarroService
import br.com.jordilucas.carros.extensions.loadUrl
import br.com.jordilucas.carros.extensions.setupToolbar
import br.com.jordilucas.carros.extensions.toast
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.startActivity

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_editar -> {
                startActivity<CarroFormActivity>("carro" to carro)
                finish()

            }
            R.id.action_deletar -> {
                alert(R.string.msg_confirma_excluir_carro, R.string.app_name) {
                    positiveButton(R.string.sim){
                        taskExcluir()
                    }
                    negativeButton(R.string.nao){

                    }
                }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun taskExcluir(){
        doAsync {
            val response = CarroService.delete(carro)
            uiThread {
                toast(response!!.msg)
                finish()
            }
        }
    }

}
