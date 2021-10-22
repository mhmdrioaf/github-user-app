package com.ananta.githubusersapp

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity, private val getUserData: String) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment.newInstance(0, getUserData)
            1 -> fragment = FollowingFragment.newInstance(1, getUserData)
        }
        return fragment as Fragment
    }
}