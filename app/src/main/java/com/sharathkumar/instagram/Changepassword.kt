package com.sharathkumar.instagram

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.sharathkumar.instagram.databinding.ActivityChangepasswordBinding

class Changepassword : AppCompatActivity() {


    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser



    private lateinit var binding:ActivityChangepasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityChangepasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

      binding.btnchangepass.setOnClickListener {
          if (binding.edtcurpass.text.toString() == "") {
              Toast.makeText(
                  this@Changepassword,
                  "Please Enter Your Current Pasword",
                  Toast.LENGTH_SHORT
              ).show()
          } else if (binding.edtnewpass.text.toString() == "") {
              Toast.makeText(
                  this@Changepassword,
                  "Please Enter Your New Password",
                  Toast.LENGTH_SHORT
              ).show()
          } else if (binding.edtrepass.text.toString() == "") {
              Toast.makeText(
                  this@Changepassword,
                  "Please Re-Enter Your New Password",
                  Toast.LENGTH_SHORT
              ).show()
          } else if (binding.edtnewpass.text.toString().length < 6) {
              Toast.makeText(
                  this@Changepassword,
                  "Password must be 6 characters",
                  Toast.LENGTH_SHORT
              ).show()
          } else if (binding.edtnewpass.text.toString() != binding.edtrepass.text.toString()) {
              Toast.makeText(this@Changepassword, "Passwords doesn't match", Toast.LENGTH_SHORT)
                  .show()
          } else {
              val currentPassword = binding.edtcurpass.text.toString().trim()
              val credential = EmailAuthProvider.getCredential(user!!.email!!, currentPassword)
              user.reauthenticate(credential)


              user.reauthenticate(credential)
                  .addOnCompleteListener { reauthTask ->
                      if (reauthTask.isSuccessful) {
                          // Step 2: Update the password
                          val newPassword = binding.edtnewpass.text.toString().trim()

                          user.updatePassword(newPassword)
                              .addOnCompleteListener { updateTask ->
                                  if (updateTask.isSuccessful) {
                                      Toast.makeText(
                                          this,
                                          "Password updated successfully!",
                                          Toast.LENGTH_SHORT
                                      ).show()
                                      binding.edtcurpass.setText("")
                                      binding.edtnewpass.setText("")
                                      binding.edtrepass.setText("")
                                  } else {
                                      Toast.makeText(
                                          this,
                                          "Error updating password: ${updateTask.exception?.message}",
                                          Toast.LENGTH_SHORT
                                      ).show()
                                  }
                              }
                      } else {
                          Toast.makeText(
                              this,
                              "Re-authentication failed: ${reauthTask.exception?.localizedMessage}",
                              Toast.LENGTH_SHORT
                          ).show()
                      }
                  }
          }
      }

    }
}