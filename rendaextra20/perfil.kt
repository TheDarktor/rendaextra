package com.rendaextra20

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rendaextra20.utils.ValueListenerAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.content_perfil.*

@Suppress("unused", "UsePropertyAccessSyntax", "SpellCheckingInspection", "TypeParameterFindViewById")
class perfil : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "ProfileActivity"
    private lateinit var mUser: Userr
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
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

        retrieve()


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
            R.id.nav_servicos -> {
                val intent = Intent(this, servicos::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_sair -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, main_login::class.java)
                startActivity(intent)
                finish()

            }
            R.id.nav_ajuda -> {
                val intent = Intent(this, ajuda::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_sobre -> {
                val intent = Intent(this, sobre::class.java)
                startActivity(intent)
                finish()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun retrieve() {
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        fun currentUserReference(): DatabaseReference =
            mDatabase.child("users").child(mAuth.currentUser!!.uid)

        currentUserReference().addListenerForSingleValueEvent(
            ValueListenerAdapter {
                mUser = it.asUser()!!
                perfil_nome.setText("Nome: "+mUser.username1)
                perfil_sobrenome.setText("Sobrenome: "+mUser.username2)
                perfil_phone.setText("Telefone: "+mUser.phone)
                perfil_cpf.setText("CPF: "+mUser.cpf)
                perfil_nasc.setText("Nascimento: "+mUser.datanasc)
                perfil_bairro.setText("Bairro: "+mUser.bairro)
                perfil_rua.setText("Rua: "+mUser.rua)
                perfil_numero.setText("Numero: "+mUser.casa)
                perfil_comp.setText("Complemento: "+mUser.comp)

            })

    }
}

