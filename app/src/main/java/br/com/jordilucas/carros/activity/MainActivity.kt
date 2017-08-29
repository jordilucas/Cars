package br.com.jordilucas.carros.activity

import android.os.Bundle
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.extensions.setupToolbar

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.toolbar)
    }
}
