package com.sharathkumar.instagram

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sharathkumar.instagram.databinding.FragmentUploadpostsBinding


class uploadposts : Fragment() {
    lateinit var binding:FragmentUploadpostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentUploadpostsBinding.inflate(layoutInflater,container,false)
        return  binding.root


    }

    override fun onStart() {
        super.onStart()
        val imageUris = loadImages(requireContext())

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = ImageAdapter(imageUris) { uri ->
            onImageSelected(uri)
        }
    }

    private fun onImageSelected(uri: Uri) {
        val intent = Intent(requireContext(),photouploadingfinal::class.java)
        intent.putExtra("selectedImageUri", uri.toString())
         startActivity(intent)
    }

}