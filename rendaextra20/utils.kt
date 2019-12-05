package com.rendaextra20

import com.google.firebase.database.DataSnapshot

fun DataSnapshot.asUser(): Userr? =
    getValue(Userr::class.java)?.copy(uid = key)