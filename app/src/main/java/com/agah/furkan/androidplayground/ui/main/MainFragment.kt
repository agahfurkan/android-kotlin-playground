package com.agah.furkan.androidplayground.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.databinding.FragmentMainBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
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
        viewPagerAdapter = MainFragmentViewPagerAdapter(this)
        binding.mainViewPager.apply {
            offscreenPageLimit = viewPagerAdapter!!.itemCount - 1
            adapter = viewPagerAdapter
        }

        binding.mainBottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_nav_home_item -> {
                    binding.mainViewPager.currentItem = 0
                }
                R.id.main_nav_cart_item -> {
                    binding.mainViewPager.currentItem = 1
                }
                else -> binding.mainViewPager.currentItem = 0
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.getUserCart()
    }

    private fun initObservers() {
        sharedViewModel.userCartResponse.observe(viewLifecycleOwner) { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    val listSize = apiResponse.data.cartList?.size ?: 0
                    val badge = binding.mainBottomNavView.getOrCreateBadge(R.id.main_nav_cart_item)
                    if (listSize > 0) {
                        badge.isVisible = true
                        badge.number = listSize
                    } else {
                        badge.isVisible = false
                    }
                }
                is ApiErrorResponse -> {
                }
            }
        }
    }
}
