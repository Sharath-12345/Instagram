package com.sharathkumar.instagram

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.sharathkumar.instagram.databinding.ActivityEditprofileBinding

class Editprofile : AppCompatActivity() {

   lateinit var userdetails:DocumentSnapshot
   lateinit var username:String
   lateinit var userbio:String

    private lateinit var binding: ActivityEditprofileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityEditprofileBinding.inflate(layoutInflater)


        setContentView(binding.root)

        FirebaseFirestore.getInstance().collection("users").
        document(FirebaseAuth.getInstance().currentUser!!.uid).collection("userdetails")
            .document("user").get().addOnSuccessListener {
            userdetails=it
            username=userdetails.get("name").toString()
            userbio=userdetails.get("bio").toString()

           binding.edtedtname.setText(username)
            binding.edtedtbio.setText(userbio)
        }

        binding.btnchange.setOnClickListener {
            if(username==binding.edtedtname.text.toString() &&
                userbio==binding.edtedtbio.text.toString())
            {
                Toast.makeText(this@Editprofile,"No Changes Have Been Made",Toast.LENGTH_LONG).show()

            }
            else if(username!=binding.edtedtname.text.toString())
            {
                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).collection("userdetails").document("user").update("name",binding.edtedtname.text.toString()).addOnSuccessListener {
                    Toast.makeText(this@Editprofile,"Name Changed Sucessfully",Toast.LENGTH_SHORT).show()
                }
            }

            else if(username!=binding.edtedtbio.text.toString())
            {
                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).collection("userdetails").document("user").update("bio",binding.edtedtbio.text.toString()).addOnSuccessListener {
                    Toast.makeText(this@Editprofile,"Bio Changed Sucessfully",Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).collection("userdetails").document("user").update("name",binding.edtedtname.text.toString()).addOnSuccessListener {
                }
                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).collection("userdetails").document("user").update("bio",binding.edtedtbio.text.toString()).addOnSuccessListener {
                }
                Toast.makeText(this@Editprofile,"Details Changed Sucessfully",Toast.LENGTH_SHORT).show()

            }

        }

    }
}