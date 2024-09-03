package com.sharathkumar.instagram.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sharathkumar.instagram.UserDetails
import com.sharathkumar.instagram.databinding.FragmentHomeeBinding
import com.sharathkumar.instagram.userposthomeezoomadapter
import com.sharathkumar.instagram.userpostszoomadapter
import kotlin.properties.Delegates


class homee : Fragment() {

    private lateinit var binding:FragmentHomeeBinding


    val TotaluserPosts = mutableListOf<UserDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeeBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        fetchPosts()
    }
    fun fetchPosts() {
        var name=""
        var db=FirebaseFirestore.getInstance()
        TotaluserPosts.clear()

        FirebaseAuth.getInstance().currentUser?.uid?.let { userId ->
            db.collection("posts").get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    for (document in queryDocumentSnapshots) {
                        val post = document.toObject(UserDetails::class.java)
                        TotaluserPosts.add(post)
                    }
                    binding.recyclerViewhomee.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerViewhomee.adapter= userposthomeezoomadapter(requireContext(),TotaluserPosts)


                }.addOnFailureListener {

                }
        }




    }

}

