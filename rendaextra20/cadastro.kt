package com.rendaextra20

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_main_login.*

class cadastro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_cadastro)

        //FUNCAO DE CADASTRO
        cad_btn_register.setOnClickListener {
            performRegister()
        }

        //---INICIO DE BLOCO: BOTÃO VOLTAR

        cad_btn_back.setOnClickListener {
            val intent = Intent(this, main_login::class.java)
            startActivity(intent)
            finish()

            //---FIM DE BLOCO: BOTÃO VOLTAR

        }

    }




    //---INICIO BLOCO: FUNCAO DE CADASTRO

    private fun performRegister(){

        val email = cad_login_text.text.toString()
        val password = cad_password_text.text.toString()

        //---INICIO BLOCO: TOASTS, E LOGCAT

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Preencha os campos de Email e Senha", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("cadastro", "Email is: " + email)
        Log.d("cadastro", "Password: $password")

        //---FIM BLOCO: TOASTS, E LOGCAT




        //---INICIO BLOCO: CADASTRO FIREBASE


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)

            .addOnCompleteListener{

                if(!it.isSuccessful) return@addOnCompleteListener

                //se cadastrado com sucesso
                Log.d("cadastro", "Successfully created user with uid: ${it.result?.user?.uid}")
                val intent = Intent(this, cad_perfil::class.java)
                startActivity(intent)
                finish()
            }


            //se NÃO cadastrado com sucesso
            .addOnFailureListener{
                Log.d("cadastro", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Email ou Senha inválidos", Toast.LENGTH_SHORT).show()
            }


        //---FIM BLOCO: CADASTRO FIREBASE
    }

    //---FIM BLOCO: FUNCAO DE CADASTRO

}
