package com.agah.furkan.androidplayground.ui.main.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.response.CartResponse
import com.agah.furkan.androidplayground.databinding.FragmentCartBinding
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.CartListAdapter
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.showLongToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment(), CartListAdapter.CartListAdapterListener {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    private var cartListAdapter: CartListAdapter? = null
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
        cartListAdapter = CartListAdapter(this)
        binding.cartList.adapter = cartListAdapter
        initObservers()
    }

    private fun initObservers() {
        sharedViewModel.userCartResponse.observe(viewLifecycleOwner) { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    cartListAdapter?.submitList(apiResponse.data.cartList)
                }
                is ApiErrorResponse -> {
                }
            }
        }
        sharedViewModel.removeProductFromCart.observe(viewLifecycleOwner) { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    if (apiResponse.data.isSuccess) {
                        showLongToast("Product Removed")
                    }
                }
                is ApiErrorResponse -> {

                }
            }
        }
    }

    override fun onRemoveButtonClicked(item: CartResponse.Cart) {
        sharedViewModel.removeProductFromCart(item.productId)
    }
}