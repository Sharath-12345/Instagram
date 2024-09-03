package com.sharathkumar.instagram.fragments

import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.sharathkumar.instagram.ImageAdapter
import com.sharathkumar.instagram.R
import com.sharathkumar.instagram.VpAdapter
import com.sharathkumar.instagram.databinding.FragmentProfileBinding
import com.sharathkumar.instagram.loadImages
import com.sharathkumar.instagram.profilemenuu
import java.io.IOException
import java.security.Permission


class profile : Fragment() {

    lateinit var userdetails:DocumentSnapshot

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {

            FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                .collection("userdetails").document("user").update("dp",uri.toString()).addOnSuccessListener {

                }


           binding.profileImage.setImageURI(uri)

        }
    }


    private lateinit var binding:FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root


        val imageUris = loadImages(requireContext())





    }



    companion object {

    }

    override fun onStart() {
        super.onStart()


        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).collection("userdetails").document("user").get().addOnSuccessListener {
           userdetails=it
            val username:String
            username= userdetails.get("name").toString()
            binding.userbio.setText(userdetails.get("bio").toString())
            binding.username.setText(username)

           var userdpstring=""
                userdpstring=userdetails.get("dp").toString()
            if(userdpstring!="")
            {

                var lastSlashIndex = -1

                for (i in userdpstring.indices) {
                    if (userdpstring[i] == '/') {
                        lastSlashIndex = i
                    }
                }


                val userdpnumber= if (lastSlashIndex != -1) {
                    userdpstring.substring(lastSlashIndex + 1)
                } else {
                    null
                }


                val userdp="content://media/external/images/media/"


                Glide.with(this)
                    .load(userdp+userdpnumber)
                    .into(binding.profileImage)

            }

        }
        binding.viewpager.adapter=VpAdapter(this)

        TabLayoutMediator(binding.tabLayout2,binding.viewpager){tab,position->
            when(position)
            {
                0->tab.text="Posts"
                1->tab.text="Reels"

            }
        }.attach()


        binding.imgprofilemenu.setOnClickListener {
            var intent=Intent(activity,profilemenuu::class.java)
           startActivity(intent)
        }

        binding.profileImage.setOnClickListener{

            pickImageLauncher.launch("image/*")
        }


    }

}