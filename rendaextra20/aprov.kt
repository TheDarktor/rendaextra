package com.rendaextra20

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_aprov.*

class aprov : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprov)

        aprov_avan.setOnClickListener {
            val intent = Intent(this, cod1::class.java)
            startActivity(intent)
            finish()
        }
    }
}
