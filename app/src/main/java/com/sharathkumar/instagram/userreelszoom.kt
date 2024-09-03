package com.sharathkumar.instagram

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sharathkumar.instagram.databinding.ActivityUserreelszoomBinding

class userreelszoom : AppCompatActivity() {

    val userReels = mutableListOf<UserDetailsReels>()
    lateinit var binding: ActivityUserreelszoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserreelszoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firestore = FirebaseFirestore.getInstance().collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid).collection("reels")
        firestore.get().addOnSuccessListener {

            for (document in it) {
                val post = document.toObject(UserDetailsReels::class.java)
                userReels.add(post)
            }
            binding.recyclerViewuserpostzoom.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewuserpostzoom.adapter=userreelszoomadapter(this,userReels)

        }
    }
}