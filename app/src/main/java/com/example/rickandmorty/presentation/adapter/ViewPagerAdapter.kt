package com.example.rickandmorty.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rickandmorty.presentation.fragment.CharacterFragment
import com.example.rickandmorty.presentation.fragment.EpisodeFragment
import com.example.rickandmorty.presentation.fragment.LocationFragment

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharacterFragment()

            1 -> LocationFragment()

            2 -> EpisodeFragment()

            else -> Fragment()

        }
    }

}