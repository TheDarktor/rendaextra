package com.rendaextra20

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_cad_perfil.*
import java.util.*

@Suppress("MemberVisibilityCanBePrivate", "RedundantVisibilityModifier")
class cad_perfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_cad_perfil)

        cad_btn_perfil_photo.setOnClickListener{
            Log.d("Cad_Perfil", "Try to show photo selector")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        cad_btn_perfil_avancar.setOnClickListener{

            uploadProfile()
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){

            Log.d("cad_perfil", "Photo was selected ")

            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            cad_perfil_photo.setImageBitmap(bitmap)

        }
    }

    private fun uploadProfile(){

        uploadImageToFirebaseStorage()

    }

    public fun uploadImageToFirebaseStorage(){

        if(selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("cad_perfil", "Successfully uploaded image: ${it.metadata?.path}")

        ref.downloadUrl.addOnSuccessListener {
            Log.d("cad_perfil", "File Location: $it")

            saveUserToFirebaseDatabase(it.toString())

        }
            }

    }
    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {

        val uid = FirebaseAuth.getInstance().uid ?: ""

        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid,
            cad_perfil_nome.text.toString(),
            cad_perfil_sobrenome.text.toString(),
            cad_perfil_phone.text.toString(),
            profileImageUrl, cad_perfil_cpf.text.toString(),
            cad_perfil_datanasc.text.toString(),
            cad_perfil_bairro.text.toString(),
            cad_perfil_rua.text.toString(),
            cad_perfil_casa.text.toString(),
            cad_perfil_comp.text.toString())

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("cad_perfil", "Successfully added username1, username2, phone, profileImageUrl... to database")
                val intent = Intent(this, home::class.java)
                startActivity(intent)
                finish()
            }

            .addOnFailureListener{
                Log.d("cad_perfil", "Failed to upload: ${it.message}")
                Toast.makeText(this, "Erro! Verifique todos os campos", Toast.LENGTH_SHORT).show()
            }

    }
}

class User(val uid: String,
           val username1: String,
           val username2: String,
           val phone: String,
           val profileImageUrl: String,
           val cpf: String,
           val datanasc: String,
           val bairro: String,
           val rua: String,
           val casa: String,
           val comp: String){
    constructor(): this("","","","","","","","",""
    ,"","")
}
