package com.agah.furkan.androidplayground.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentMainBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.main.child.CartFragment
import com.agah.furkan.androidplayground.ui.main.child.DiscoverFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!
    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(this)
        binding.mainViewPager.apply {
            offscreenPageLimit = viewPagerAdapter!!.itemCount - 1
            adapter = viewPagerAdapter
        }

        binding.mainBottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_nav_discover_item -> {
                    binding.mainViewPager.currentItem = 0
                }
                R.id.main_nav_cart_item -> {
                    binding.mainViewPager.currentItem = 1
                }
                else -> binding.mainViewPager.currentItem = 0
            }
            true
        }

        binding.mainViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.mainBottomNavView.selectedItemId = R.id.main_nav_discover_item
                    1 -> binding.mainBottomNavView.selectedItemId = R.id.main_nav_cart_item
                }
            }
        })
    }

    class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> DiscoverFragment()
                1 -> CartFragment()
                else -> throw RuntimeException()
            }
        }
    }
}