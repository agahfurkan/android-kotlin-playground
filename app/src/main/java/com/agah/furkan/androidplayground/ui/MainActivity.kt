package com.agah.furkan.androidplayground.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.databinding.ActivityMainBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.beginFadeTransition
import com.agah.furkan.androidplayground.util.hide
import com.agah.furkan.androidplayground.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

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
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    fun confToolbar(toolbarType: BaseFragment.ToolbarType) {
        when (toolbarType) {
            is BaseFragment.ToolbarType.None -> hideToolbar()
            is BaseFragment.ToolbarType.WithActionButtons -> showToolbar(toolbarType.enabledButton)
        }
    }

    private fun showToolbar(enabledButtons: List<BaseFragment.ToolbarType.ToolbarButton>) {
        binding.layoutMainToolbar.mainToolbarActionButtons.hide()
        enabledButtons.forEach { toolbarButton ->
            when (toolbarButton) {
                BaseFragment.ToolbarType.ToolbarButton.BACK -> {
                    binding.layoutMainToolbar.mainToolbarBtnBack.show()
                }

                BaseFragment.ToolbarType.ToolbarButton.DONE -> {
                    binding.layoutMainToolbar.mainToolbarBtnDone.show()
                }
            }
        }
        binding.layoutMainToolbar.layoutMainToolbar.beginFadeTransition(binding.mainActivityLayout)
        binding.layoutMainToolbar.layoutMainToolbar.show()
    }

    private fun hideToolbar() {
        binding.layoutMainToolbar.layoutMainToolbar.beginFadeTransition(binding.mainActivityLayout)
        binding.layoutMainToolbar.layoutMainToolbar.hide()
    }
}
