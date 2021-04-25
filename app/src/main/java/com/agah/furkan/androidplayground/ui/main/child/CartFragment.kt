package com.agah.furkan.androidplayground.ui.main.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.databinding.FragmentCartBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.main.MainFragmentVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val mainFragmentVM by viewModels<MainFragmentVM>(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        mainFragmentVM.cartResponse.observe(viewLifecycleOwner) { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {

                }
                is ApiErrorResponse -> {

                }
            }
        }
    }
}