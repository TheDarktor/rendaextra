package com.rendaextra20

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main_login.*

class main_login : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            supportActionBar!!.hide()
            setContentView(R.layout.activity_main_login)



            //FUNCAO LOGIN
            btn_login.setOnClickListener {
                performLogin()
            }



            //---INICIO BLOCO: IR PARA TELA DE CADASTRO

            btn_register.setOnClickListener {
                    val intent = Intent(this, cadastro::class.java)
                    startActivity(intent)
                    finish()
                }

            //---INICIO BLOCO: IR PARA TELA DE CADASTRO
        }



    //--INICIO BLOCO: FUNCAO LOGIN

        private fun performLogin(){

            val email = login_text.text.toString()
            val password = login_password_text.text.toString()

            //---INICIO BLOCO: TOASTS, E LOGCAT

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Preencha os campos de Email e Senha", Toast.LENGTH_SHORT).show()
                return
            }

            Log.d("main_login", "Email is: " + email)
            Log.d("main_login", "Password: $password")

            //---FIM BLOCO: TOASTS, E LOGCAT



            //---INICIO BLOCO: LOGIN FIREBASE


            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if(!it.isSuccessful) return@addOnCompleteListener

                    //se cadastrado com sucesso
                    Log.d("main_login", "Successfully logged user with uid: ${it.result?.user?.uid}")
                    val intent = Intent(this, home::class.java)
                    startActivity(intent)
                    finish()
                }


                //se NÃO cadastrado com sucesso
                .addOnFailureListener{
                    Log.d("main_login", "Failed to login: ${it.message}")
                    Toast.makeText(this, "Email ou Senha inválidos", Toast.LENGTH_SHORT).show()
                }


            //---FIM BLOCO: LOGIN FIREBASE

        }

    //--FIM BLOCO: FUNCAO LOGIN

}
