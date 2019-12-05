package com.rendaextra20.utils

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

@Suppress("unused")

class ValueListenerAdapter(val handler: (DataSnapshot) -> Unit): ValueEventListener {

    private val TAG = "ValueListenerAdapter"

    override fun onDataChange(data: DataSnapshot) {
        handler(data)
    }

    override fun onCancelled(error: DatabaseError) {
        Log.e("onCancelled", TAG, error.toException())
    }
}