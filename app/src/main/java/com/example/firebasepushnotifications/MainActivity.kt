package com.example.firebasepushnotifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fir : FirebaseAuth = FirebaseAuth.getInstance()
        msg()
     }

    private fun msg(){
        FirebaseAuth.getInstance().signInWithCustomToken("123")
        FirebaseMessaging.getInstance().subscribeToTopic("All")
    }
}