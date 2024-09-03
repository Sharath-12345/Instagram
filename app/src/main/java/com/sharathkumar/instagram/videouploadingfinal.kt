package com.sharathkumar.instagram

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sharathkumar.instagram.databinding.ActivityVideouploadingfinalBinding
import kotlin.properties.Delegates

class videouploadingfinal : AppCompatActivity() {

    private var alreadyuploadedreels =0
    private var alreadyuploadedtotalreels =0

    var userdp=""
    var username=""

    var db=FirebaseFirestore.getInstance()

    lateinit var binding:ActivityVideouploadingfinalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityVideouploadingfinalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getuserdetails()

        var videouri=intent.getStringExtra("videouri")
        binding.videoview.setVideoURI(Uri.parse(videouri))

        Glide.with(this).load(videouri).thumbnail(0.1f).into(binding.imgview)
        binding.imgview.scaleType=ImageView.ScaleType.CENTER_CROP




        binding.imgview.setOnClickListener {

                binding.imgview.visibility=View.INVISIBLE
                binding.playic.visibility=View.INVISIBLE
                binding.videoview.start()


        }

        val firestore=FirebaseFirestore.getInstance().collection("users").
        document(FirebaseAuth.getInstance().currentUser!!.uid).collection("reels")

        firestore.get().addOnSuccessListener {
            alreadyuploadedreels=it.size()
            alreadyuploadedreels+=1

        }
        db.collection("reels").get().addOnSuccessListener {
            alreadyuploadedtotalreels=it.size()
            alreadyuploadedtotalreels+=1
        }






        binding.txtpost.setOnClickListener {

            var caption=binding.edtcaption.text.toString()

            var reeldata = mutableMapOf(
                "caption" to caption,
                "video" to videouri,
                "userdp" to userdp,
                "username" to username
            )


           FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid)
               .collection("reels").document("reel"+alreadyuploadedreels).set(reeldata).addOnSuccessListener {
                   Toast.makeText(this,"Uploaded",Toast.LENGTH_SHORT).show()
                   startActivity(Intent(this@videouploadingfinal,Home::class.java))
                   finish()
               }

            db.collection("reels").document("reel"+alreadyuploadedtotalreels).set(reeldata).addOnSuccessListener {

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