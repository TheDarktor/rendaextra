package com.rendaextra20

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_serv_cad.*

class serv_cad1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serv_cad)

        snd_email.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "text/html"

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("servcad@rendaextra.com"))

            intent.putExtra(Intent.EXTRA_SUBJECT, "CADASTRO DE SERVIÇO")

            intent.putExtra(Intent.EXTRA_TEXT, "(O email deve conter:\n Nome, Sobrenome, Seu serviço e fotos do Serviço.)\n" +
                    "(Após enviado, aguarde a aprovação.)")

            startActivity(intent)
            finish()

        }
    }
}
