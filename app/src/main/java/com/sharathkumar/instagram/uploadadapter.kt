package com.sharathkumar.instagram

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sharathkumar.instagram.fragments.upload


class uploadadapter(fa: upload): FragmentStateAdapter(fa){
     override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position)
        {
            0-> uploadposts()
            1->uploadreels()
            else->uploadposts()
        }
    }
}