package com.rendaextra20

import com.google.firebase.database.Exclude

@Suppress("unused", "SpellCheckingInspection")

data class Userr(@Exclude val uid: String? = "",val username1: String = "", val username2: String = "",val rua: String = "",
                 val phone: String = "",val datanasc: String = "",val cpf: String = "",val comp: String = "",
                 val casa: String = "",val bairro: String = "",val Image: String = "")