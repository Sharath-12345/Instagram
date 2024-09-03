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
import com.google.firebase.firestore.FirebaseFirestore


class Register : AppCompatActivity() {
    private lateinit var edtemail : EditText
    private lateinit var edtname : EditText
    private lateinit var edtpassword : EditText
    private lateinit var btnregister : Button
    private lateinit var txtlogin:TextView
    private lateinit var progress:ProgressBar

    var mauth=FirebaseAuth.getInstance()
    var db= FirebaseFirestore.getInstance()
    var userbio="Just getting started!"
    var usergmailname=""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)



        findingId()

        progress.visibility=View.INVISIBLE

        btnregister.setOnClickListener {
            if (edtname.text.toString() == "" || edtemail.text.toString() == "" || edtpassword.text.toString() == "") {
                Toast.makeText(this@Register, "Please Enter the Details", Toast.LENGTH_LONG).show()
            } else if (edtname.text.toString().length < 4) {
                Toast.makeText(this@Register, "Please Enter a Valid Name", Toast.LENGTH_LONG).show()

            } else {

                creatingaccount()

            }
        }

        txtlogin.setOnClickListener {
            startActivity(Intent(this@Register,Login::class.java))
            finish()
        }
    }


    fun findingId()
    {
        edtemail=findViewById(R.id.edtgmail)
        edtname=findViewById(R.id.edtname)
        edtpassword=findViewById(R.id.edtpassword)
        btnregister=findViewById(R.id.btnregister)
        txtlogin=findViewById(R.id.txtlogin)
        progress=findViewById(R.id.progress2)
    }
    fun creatingaccount()
    {

            progress.visibility= View.VISIBLE

            mauth.createUserWithEmailAndPassword(edtemail.text.toString(),edtpassword.text.toString()).addOnCompleteListener {
                    result->
                if(result.isSuccessful)
                {

                    var userData = mutableMapOf(
                        "email" to edtemail.text.toString(),
                        "name" to edtname.text.toString(),
                        "password" to edtpassword.text.toString(),
                        "bio" to userbio,
                        "dp" to ""
                    )




                    db.collection("users").document(
                        FirebaseAuth.getInstance()
                        .currentUser!!.uid.toString()).collection("userdetails").document("user")
                        .set(userData).addOnSuccessListener {
                            progress.visibility= View.INVISIBLE
                            startActivity(Intent(this@Register,Home::class.java))
                            finish()
                            finish()

                        }.addOnFailureListener {
                            Toast.makeText(this@Register,it.localizedMessage.toString(),Toast.LENGTH_LONG).show()
                            progress.visibility= View.INVISIBLE

                        }


                }
            }
        }

    }
