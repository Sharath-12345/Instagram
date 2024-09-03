package com.sharathkumar.instagram

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sharathkumar.instagram.databinding.ActivityPhotouploadingfinalBinding
import kotlin.properties.Delegates

class photouploadingfinal : AppCompatActivity() {

  private var alreadyuploadedpost by Delegates.notNull<Int>()
    private var alreadyuploadedtotalpost by Delegates.notNull<Int>()
    var userdp=""
    var username=""

    private lateinit var binding:ActivityPhotouploadingfinalBinding

    val db=FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPhotouploadingfinalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getuserdetails()

        var imguri = intent.getStringExtra("selectedImageUri").toString()
        binding.imgfinalphotoupload.setImageURI(Uri.parse(imguri))
        binding.imgfinalphotoupload.scaleType = ImageView.ScaleType.CENTER_CROP


        val firestore=FirebaseFirestore.getInstance().collection("users").
        document(FirebaseAuth.getInstance().currentUser!!.uid).collection("posts")

       firestore.get().addOnSuccessListener {
           alreadyuploadedpost=it.size()
           alreadyuploadedpost+=1

       }

        db.collection("posts").get().addOnSuccessListener {
            alreadyuploadedtotalpost=it.size()
            alreadyuploadedtotalpost+=1
        }


        binding.txtpost.setOnClickListener {
            var caption = binding.edtcaption.text.toString()

            var postdata = mutableMapOf(
                "caption" to caption,
                "photo" to imguri,
                "userdp" to userdp,
                "username" to username
            )



            firestore.document("post"+alreadyuploadedpost).set(postdata)
                .addOnSuccessListener {
                    Toast.makeText(this@photouploadingfinal,"Uploaded",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@photouploadingfinal,Home::class.java))
                    finish()
                }
            db.collection("posts").document("post"+alreadyuploadedtotalpost).set(postdata).addOnSuccessListener {

            }




        }
    }
    fun getuserdetails()
    {
     val mauth=FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance()
         .currentUser!!.uid.toString()).collection("userdetails")
         .document("user").get().addOnSuccessListener {
         username=it.get("name").toString()
             userdp=it.get("dp").toString()
     }
    }
}