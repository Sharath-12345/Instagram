package com.sharathkumar.instagram

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sharathkumar.instagram.fragments.profile

class VpAdapter(fa: profile): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position)
        {
            0->userposts()
            1->usereels()
            else->userposts()
        }
    }
}