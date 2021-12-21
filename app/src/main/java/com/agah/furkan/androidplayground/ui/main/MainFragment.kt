package com.agah.furkan.androidplayground.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.databinding.FragmentMainBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.main.child.CartFragment
import com.agah.furkan.androidplayground.ui.main.child.CategoryFragment
import com.agah.furkan.androidplayground.ui.main.child.HomeFragment
import com.agah.furkan.androidplayground.ui.main.child.ProfileFragment
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private lateinit var viewPagerAdapter: MainFragmentViewPagerAdapter
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        viewPagerAdapter = MainFragmentViewPagerAdapter(this).apply {
            addFragment(HomeFragment.newInstance())
            addFragment(CategoryFragment.newInstance())
            addFragment(CartFragment.newInstance())
            addFragment(ProfileFragment.newInstance())
        }
        binding.mainViewPager.apply {
            offscreenPageLimit = viewPagerAdapter.itemCount - 1
            adapter = viewPagerAdapter
            isUserInputEnabled = false
        }

        binding.mainBottomNavView.setOnNavigationItemSelectedListener {
            val fragmentClass = when (it.itemId) {
                R.id.main_nav_home_item -> {
                    HomeFragment::class.java
                }
                R.id.main_nav_categories_item -> {
                    CategoryFragment::class.java
                }
                R.id.main_nav_cart_item -> {
                    CartFragment::class.java
                }
                R.id.main_nav_profile_item -> {
                    ProfileFragment::class.java
                }
                R.id.main_nav_second_module_item -> {
                    TODO()
                }
                else -> throw IllegalStateException("err")
            }
            binding.mainViewPager.currentItem = viewPagerAdapter.positionOfFragment(fragmentClass)
            true
        }
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.getUserCart()
    }

    private fun initObservers() {
        sharedViewModel.userCart.observe(viewLifecycleOwner) { userCart ->
            val listSize = userCart.size
            val badge = binding.mainBottomNavView.getOrCreateBadge(R.id.main_nav_cart_item)
            if (listSize > 0) {
                badge.isVisible = true
                badge.number = listSize
            } else {
                badge.isVisible = false
            }
        }
    }
}
