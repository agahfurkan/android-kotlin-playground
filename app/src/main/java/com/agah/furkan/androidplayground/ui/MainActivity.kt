package com.agah.furkan.androidplayground.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.ActivityMainBinding
import com.agah.furkan.androidplayground.ui.base.BaseActivity
import com.agah.furkan.androidplayground.ui.login.LoginFragmentDirections
import com.agah.furkan.androidplayground.util.SharedPrefUtil

class MainActivity : BaseActivity(), View.OnClickListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activityMoreButton.setOnClickListener(this)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> {
                    binding.activityMoreButton.visibility = View.VISIBLE
                }
                else -> {
                    binding.activityMoreButton.visibility = View.GONE
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.activityMoreButton -> {
                val popup = PopupMenu(this, v)
                popup.menuInflater.inflate(R.menu.activity_more_menu, popup.menu)
                popup.setOnMenuItemClickListener { menuItem: MenuItem ->
                    when (menuItem.itemId) {
                        R.id.main_menu_profile_action -> {

                        }
                        R.id.main_menu_logout_action -> {
                            SharedPrefUtil.setToken(null)
                            navController.navigate(LoginFragmentDirections.actionGlobalLoginFragment())
                        }
                    }
                    return@setOnMenuItemClickListener true
                }
                popup.show()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
