package com.sharathkumar.instagram

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sharathkumar.instagram.databinding.FragmentUserpostsBinding


class userposts : Fragment() {
    var imguri = ""
    val imageUris: MutableList<String> = mutableListOf()

    private lateinit var binding: FragmentUserpostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserpostsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()


        val firestore = FirebaseFirestore.getInstance().collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid).collection("posts")
        firestore.get().addOnSuccessListener {
            val userPosts = mutableListOf<UserDetails>()

            for (document in it) {
                val post = document.toObject(UserDetails::class.java)
                userPosts.add(post)
                imguri = document.getString("photo").toString()

                imageUris.add((imguri.toString()))

            }
            val imageUriss: MutableList<Uri> = mutableListOf()
            for (one in imageUris) {
                imageUriss.add(Uri.parse(one))
            }
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.recyclerView.adapter = userpostsadapter(requireContext(),imageUriss)

        }
    }


}
