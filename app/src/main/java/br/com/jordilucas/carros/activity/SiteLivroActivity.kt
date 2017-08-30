package br.com.jordilucas.carros.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ProgressBar
import br.com.jordilucas.carros.R

class SiteLivroActivity : AppCompatActivity() {

    private val URL_SOBRE = "http://ww.livroandroid.com.br/sobre.htm"
    var webview: WebView? = null
    var progress: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_livro)
    }
}
