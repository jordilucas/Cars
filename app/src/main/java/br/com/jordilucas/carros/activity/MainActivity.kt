package br.com.jordilucas.carros.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.adapter.TabsAdapter
import br.com.jordilucas.carros.extensions.setupToolbar
import br.com.jordilucas.carros.extensions.toast
import br.com.jordilucas.carros.utils.Prefs
import br.com.jordilucas.carros.domain.TipoCarro
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.toolbar)
        setupNavDrawer()
        setupViewPagerTabs()

        fab.setOnClickListener(){
            startActivity<CarroFormActivity>()
        }

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

    private fun setupViewPagerTabs(){
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = TabsAdapter(context, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        val cor = ContextCompat.getColor(context, R.color.white)
        tabLayout.setTabTextColors(cor, cor)
        tabLayout.isHorizontalScrollBarEnabled = true
        val tabIdx = Prefs.getInt("tabIdx")
        viewPager.currentItem = tabIdx
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int){}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                Prefs.tabIdx = position
            }
        })

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_item_carros_todos -> {
                toast("Clicou em carros")
            }
            R.id.nav_item_carros_classicos -> {
                startActivity<CarrosActivity>("tipo" to TipoCarro.classicos)
            }
            R.id.nav_item_carros_esportivos -> {
                startActivity<CarrosActivity>("tipo" to TipoCarro.esportivos)
            }
            R.id.nav_item_carros_luxo -> {
                startActivity<CarrosActivity>("tipo" to TipoCarro.luxo)
            }
            R.id.nav_item_site_livro -> {
                startActivity<SiteLivroActivity>()
            }
            R.id.nav_item_settings -> {
                toast("Clicou em settings")
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
