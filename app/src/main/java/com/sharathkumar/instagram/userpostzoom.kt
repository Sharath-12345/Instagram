package com.sharathkumar.instagram

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sharathkumar.instagram.databinding.ActivityUserpostzoomBinding

class userpostzoom : AppCompatActivity() {

    val userPosts = mutableListOf<UserDetails>()

    private lateinit var binding:ActivityUserpostzoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityUserpostzoomBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val firestore = FirebaseFirestore.getInstance().collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid).collection("posts")
        firestore.get().addOnSuccessListener {

            for (document in it) {
                val post = document.toObject(UserDetails::class.java)
                userPosts.add(post)

            }
            binding.recyclerViewuserpostzoom.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewuserpostzoom.adapter=userpostszoomadapter(this,userPosts)

        }









    }
}