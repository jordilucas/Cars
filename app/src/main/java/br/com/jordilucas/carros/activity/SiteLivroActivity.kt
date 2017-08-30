package br.com.jordilucas.carros.activity

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.dialogs.AboutDialog
import br.com.jordilucas.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_site_livro.*


class SiteLivroActivity : AppCompatActivity() {

    private val URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_livro)
        val actionBar = setupToolbar(R.id.toolbar)
        actionBar.setDisplayHomeAsUpEnabled(true)

        setWebViewClient(webview)
        webview.loadUrl(URL_SOBRE)

        swipeRefresh.setOnRefreshListener { webview.reload() }

        swipeRefresh.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3
        )

    }

    private fun setWebViewClient(webView: WebView?){
        webView?.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress.visibility = View.INVISIBLE
                swipeRefresh.isRefreshing = false
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

                val url = request?.url.toString()
                if(url.endsWith("sobre.htm")){
                    AboutDialog.showAbout(supportFragmentManager)
                    return true
                }

                return super.shouldOverrideUrlLoading(view, request)
            }

        }
    }
}
