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
    private var viewPagerAdapter: MainFragmentViewPagerAdapter? = null
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
            offscreenPageLimit = viewPagerAdapter!!.itemCount - 1
            adapter = viewPagerAdapter
            isUserInputEnabled = false
        }

        binding.mainBottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_nav_home_item -> {
                    binding.mainViewPager.currentItem = 0
                }
                R.id.main_nav_categories_item -> {
                    binding.mainViewPager.currentItem = 1
                }
                R.id.main_nav_cart_item -> {
                    binding.mainViewPager.currentItem = 2
                }
                R.id.main_nav_profile_item -> {
                    binding.mainViewPager.currentItem = 3
                }
                R.id.main_nav_second_module_item -> {
                    TODO()
                }
                else -> throw IllegalStateException()
            }
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
