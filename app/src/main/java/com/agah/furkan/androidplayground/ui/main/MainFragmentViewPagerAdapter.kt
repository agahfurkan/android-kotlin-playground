package com.agah.furkan.androidplayground.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainFragmentViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragmentList = arrayListOf<Fragment>()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment) = fragmentList.add(fragment)
    fun removeFragment(fragment: Fragment) = fragmentList.remove(fragment)

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}