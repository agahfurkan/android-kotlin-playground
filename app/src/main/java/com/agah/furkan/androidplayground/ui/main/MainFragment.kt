package com.agah.furkan.androidplayground.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
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
    private val sharedViewModel by activityViewModels<SharedViewModel>()

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
        initObservers()
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
