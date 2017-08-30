package br.com.jordilucas.carros.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.extensions.setupToolbar
import br.com.jordilucas.carros.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.toolbar)
        setupNavDrawer()
    }

    private fun setupNavDrawer(){
        val toogle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toogle)
        toogle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_item_carros_todos -> {
                toast("Clicou em carros")
            }
            R.id.nav_item_carros_classicos -> {
                toast("Clicou em classicos")
            }
            R.id.nav_item_carros_esportivos -> {
                toast("Clicou em esportivos")
            }
            R.id.nav_item_carros_luxo -> {
                toast("Clicou em luxo")
            }
            R.id.nav_item_site_livro -> {
                toast("Clicou em livro")
            }
            R.id.nav_item_settings -> {
                toast("Clicou em settings")
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
