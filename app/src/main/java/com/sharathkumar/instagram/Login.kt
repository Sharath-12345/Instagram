package com.sharathkumar.instagram

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var loginemail:EditText
    lateinit var loginpassword: EditText
    lateinit var btnlogin: Button
    lateinit var txtregister:TextView
    lateinit var progress:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        findingId()
        progress.visibility=View.INVISIBLE

        txtregister.setOnClickListener {
            startActivity(Intent(this@Login,Register::class.java))
            finish()
        }

        btnlogin.setOnClickListener {
            if(loginemail.text.toString()=="" || loginpassword.text.toString()=="")
            {
                Toast.makeText(this@Login,"Please Enter the Details",Toast.LENGTH_SHORT).show()
            }
            else
            {
                progress.visibility=View.VISIBLE
                FirebaseAuth.getInstance().signInWithEmailAndPassword(loginemail.text.toString(),
                    loginpassword.text.toString()).addOnCompleteListener {
                    result->
                        if(result.isSuccessful)
                        {
                            progress.visibility=View.INVISIBLE
                            startActivity(Intent(this@Login,Home::class.java))
                            finish()
                        }
                       else
                        {
                            progress.visibility=View.INVISIBLE
                            Toast.makeText(this@Login,"Account not Found",Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }



    }
    fun findingId()
    {
        loginemail=findViewById(R.id.edtloginemail)
        loginpassword=findViewById(R.id.edtloginpass)
        btnlogin=findViewById(R.id.btnlogin)
        txtregister=findViewById(R.id.txtregister)
        progress=findViewById(R.id.progress)
    }
}