package com.sharathkumar.instagram

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.sharathkumar.instagram.databinding.ActivityProfilemenuuBinding
import com.sharathkumar.instagram.databinding.CustomdialogBinding

class profilemenuu : AppCompatActivity() {

   private lateinit var binding: ActivityProfilemenuuBinding


   private lateinit var dialog:Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityProfilemenuuBinding.inflate(layoutInflater)


        setContentView(binding.root)




       binding.topAppBar.setOnClickListener {
            finish()
        }

        binding.layouteditprofile.setOnClickListener{
            var intent=Intent(this@profilemenuu,Editprofile::class.java)
            startActivity(intent)
        }

        binding.layoutchangepassword.setOnClickListener {
            var intent=Intent(this@profilemenuu,Changepassword::class.java)
            startActivity(intent)
        }

        binding.layoutlogout.setOnClickListener {

            lateinit var bindings: CustomdialogBinding
            bindings=CustomdialogBinding.inflate(layoutInflater)
            dialog=Dialog(this@profilemenuu)
            dialog.setContentView(bindings.root)
            dialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window!!.setBackgroundDrawable(getDrawable(R.drawable.custom_diolog_box))
            dialog.show()


            bindings.btnno.setOnClickListener {
                dialog.dismiss()

            }
            bindings.btnyes.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, Login::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }






    }
}