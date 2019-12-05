package com.rendaextra20

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.content_servicos.*

class servicos : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    internal lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicos)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        serv_cad.setOnClickListener {

            val intent = Intent(this, serv_cad1::class.java)
            startActivity(intent)
            finish()
        }

        viewpager1()
        cont1()


    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_inicio -> {
                val intent = Intent(this, home::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_sair -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, main_login::class.java)
                startActivity(intent)
                finish()

            }
            R.id.nav_perfil -> {
                val intent = Intent(this, perfil::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_ajuda -> {
                val intent = Intent(this, ajuda::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_sobre-> {
                val intent = Intent(this, sobre::class.java)
                startActivity(intent)
                finish()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun viewpager1(){
    viewPager = findViewById(R.id.viewPager) as ViewPager
    val adapter = ViewPageAdapter(this)
    viewPager.adapter = adapter
}

    private fun cont1(){
        serv_cont1.setOnClickListener{
            val intent = Intent(this, aprov::class.java)
            startActivity(intent)
            finish()
        }
    }

}
