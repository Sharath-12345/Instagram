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
import com.sharathkumar.instagram.databinding.FragmentUsereelsBinding


class usereels : Fragment() {

    lateinit var binding : FragmentUsereelsBinding
    var videouri=""
    var videoUris: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentUsereelsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()


        val firestore = FirebaseFirestore.getInstance().collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid).collection("reels")
        firestore.get().addOnSuccessListener {
            val userReels = mutableListOf<UserDetails>()


            for (document in it) {
                val post = document.toObject(UserDetails::class.java)
                userReels.add(post)
                videouri = document.getString("video").toString()

                videoUris.add((videouri.toString()))

            }
            val videoUriss: MutableList<Uri> = mutableListOf()
            for (one in videoUris) {
                videoUriss.add(Uri.parse(one))
            }

            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.recyclerView.adapter = userreelsadapter(requireContext(),videoUriss)
        }
    }
}