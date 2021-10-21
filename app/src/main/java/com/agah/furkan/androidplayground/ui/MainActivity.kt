package com.agah.furkan.androidplayground.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.databinding.ActivityMainBinding
import com.agah.furkan.androidplayground.ui.base.BaseActivity
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.beginFadeTransition
import com.agah.furkan.androidplayground.util.hide
import com.agah.furkan.androidplayground.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), View.OnClickListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }
    private val sharedViewModel by viewModels<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.layoutMainToolbar.mainToolbarBtnBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.layoutMainToolbar.mainToolbarBtnBack -> {
                navController.navigateUp()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    fun confToolbar(toolbarType: BaseFragment.ToolbarType) {
        when (toolbarType) {
            BaseFragment.ToolbarType.NONE -> hideToolbar()
            BaseFragment.ToolbarType.BACK -> showToolbar()
        }
    }

    private fun showToolbar() {
        binding.layoutMainToolbar.layoutMainToolbar.beginFadeTransition(binding.mainActivityLayout)
        binding.layoutMainToolbar.layoutMainToolbar.show()
    }

    private fun hideToolbar() {
        binding.layoutMainToolbar.layoutMainToolbar.beginFadeTransition(binding.mainActivityLayout)
        binding.layoutMainToolbar.layoutMainToolbar.hide()
    }
}
