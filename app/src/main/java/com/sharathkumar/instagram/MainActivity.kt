package com.sharathkumar.instagram

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

         Handler(Looper.getMainLooper()).postDelayed({
             if(FirebaseAuth.getInstance().currentUser!=null)
             {
                 val intent=Intent(this@MainActivity, Home::class.java)
                 startActivity(intent)
                 finish()
             }
             else
             {
                 val intent=Intent(this@MainActivity,Register::class.java)
                 startActivity(intent)
                 finish()
             }
         },1000)
    }
}