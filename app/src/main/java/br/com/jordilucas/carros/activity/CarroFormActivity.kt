package br.com.jordilucas.carros.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.domain.Carro
import br.com.jordilucas.carros.domain.CarroService
import br.com.jordilucas.carros.extensions.*
import br.com.jordilucas.carros.domain.TipoCarro
import br.com.jordilucas.carros.utils.CameraHelper
import kotlinx.android.synthetic.main.activity_carro_form.*
import kotlinx.android.synthetic.main.activity_carro_form_content.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CarroFormActivity : BaseActivity() {

    val carro: Carro? by lazy { intent.getParcelableExtra<Carro>("carro") }
    val camera = CameraHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro_form)
        setupToolbar(R.id.toolbar, carro?.nome?: getString(R.string.novo_carro), true)
        initViews()
        camera.init(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        camera.onSaveInstanceState(outState)
    }

    fun initViews(){
        appBarImg.onClick { onCLickAppBarImg() }
        carro?.apply {
            appBarImg.loadUrl(carro?.urlFoto)
            tDesc.string = desc
            tNome.string = nome
            when(tipo){
                "classicos" -> radioTipo.check(R.id.tipoClassico)
                "esportivos" -> radioTipo.check(R.id.tipoEsportivo)
                "luxo" -> radioTipo.check(R.id.tipoLuxo)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form_carro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_salvar -> taskSalvar()
        }
        return super.onOptionsItemSelected(item)
    }

    fun taskSalvar(){
        if(tNome.isEmpty()){
            tNome.error = getString(R.string.msg_error_form_nome)
            return
        }
        if(tDesc.isEmpty()){
            tDesc.error = getString(R.string.msg_error_form_desc)
            return
        }

        val dialog = ProgressDialog.show(this, "Download", "Salvando o carro, aguarde...",
                false, true)

        doAsync {
            val c = carro?:Carro()
            c.nome = tNome.string
            c.desc = tDesc.string

            c.tipo = when(radioTipo.checkedRadioButtonId){
                R.id.tipoClassico -> TipoCarro.classicos.name
                R.id.tipoEsportivo -> TipoCarro.esportivos.name
                else -> TipoCarro.luxo.name
            }

            val file = camera.file
            if(file != null && file.exists()){
                val response = CarroService.postFoto(file)
                if(response!!.isOK()){
                    c.urlFoto = response!!.url
                }
            }

            val response = CarroService.save(c)
            uiThread {
                toast(response!!.msg)
                dialog.dismiss()
                finish()
            }
        }
    }

    fun onCLickAppBarImg(){
        val ms = System.currentTimeMillis()
        val fileName = if(carro != null)
            "foto_carro_${carro?.id}.jpg" else "foto_carro_${ms}.jpg"
        val intent = camera.open(this, fileName)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            val bitmap = camera.getBitmap(600,600)
            if(bitmap != null){
                camera.save(bitmap)
                appBarImg.setImageBitmap(bitmap)
            }
        }
    }
}
