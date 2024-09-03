package com.sharathkumar.instagram.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore

import com.sharathkumar.instagram.UserDetailsReels

import com.sharathkumar.instagram.databinding.FragmentReelsBinding
import com.sharathkumar.instagram.reelshowingadapter


class reels : Fragment() {

    private lateinit var binding:FragmentReelsBinding
     val db=FirebaseFirestore.getInstance()
    val TotaluserReels = mutableListOf<UserDetailsReels>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentReelsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        fetchreels()
    }



  fun fetchreels()
  {
      db.collection("reels").get().addOnSuccessListener {
          for(document in it)
          {
              val reel= document.toObject(UserDetailsReels::class.java)
              TotaluserReels.add(reel)
          }
          binding.recyclerViewreels.layoutManager= LinearLayoutManager(requireContext())
          binding.recyclerViewreels.adapter=reelshowingadapter(requireContext(),TotaluserReels)
      }
  }
}