package br.com.jordilucas.carros.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.domain.Carro
import br.com.jordilucas.carros.domain.CarroService
import br.com.jordilucas.carros.domain.FavoritosService
import br.com.jordilucas.carros.extensions.loadUrl
import br.com.jordilucas.carros.extensions.setupToolbar
import br.com.jordilucas.carros.extensions.toast
import br.com.jordilucas.carros.fragments.MapaFragment
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
        fab.setOnClickListener { onClickFavoritar(carro) }
    }

    override fun onResume() {
        super.onResume()
        taskUpdateFavoriteColor()
    }

    fun initViews(){
        Log.i("test", carro.toString())
        tDesc.text = carro.desc
        appBarImg.loadUrl(carro.urlFoto)
        img.loadUrl(carro.urlFoto)
        val url = carro.urlVideo
        imgPlayVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(url), "video/*")
            startActivity(intent)
        }
        val mapaFragment = MapaFragment()
        mapaFragment.arguments = intent.extras
        supportFragmentManager.beginTransaction().replace(R.id.mapaFragment, mapaFragment).commit()
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

    fun taskUpdateFavoriteColor(){
        doAsync {
            val b = FavoritosService.isFavorito(carro)
            uiThread {
                setFavoriteColor(b)
            }
        }
    }

    fun onClickFavoritar(carro: Carro){
        doAsync {
            val favoritado = FavoritosService.favoritar(carro)
            uiThread {
                toast(if(favoritado) R.string.msg_carro_favoritado
                        else R.string.msg_carro_desfavoritado)

                setFavoriteColor(favoritado)

            }
        }
    }

    fun setFavoriteColor(favorito: Boolean){
        val fundo = ContextCompat.getColor(this, if(favorito) R.color.favorito_on else
                R.color.favorito_off)
        val cor = ContextCompat.getColor(this, if(favorito) R.color.yellow else R.color.favorito_on)
        fab.backgroundTintList = ColorStateList(arrayOf(intArrayOf(0)), intArrayOf(fundo))
        fab.setColorFilter(cor)
    }

}
