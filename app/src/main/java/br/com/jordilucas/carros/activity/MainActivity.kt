package br.com.jordilucas.carros.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.jordilucas.carros.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
