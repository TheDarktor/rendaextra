@file:Suppress("ClassName", "SpellCheckingInspection")

package com.rendaextra20

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ajuda_pub.*

class ajuda_pub1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajuda_pub)
        ajudapubvoltar()
    }

    private fun ajudapubvoltar(){
        ajuda_pub_voltar.setOnClickListener{
            val intent = Intent(this, ajuda::class.java)
            startActivity(intent)
            finish()
        }
    }
}
