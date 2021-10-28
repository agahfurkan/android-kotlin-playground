package com.agah.furkan.androidplayground.ui.main.child

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.data.domain.model.Cart
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.databinding.FragmentCartBinding
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.CartListAdapter
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.GenericListAdapter
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.showLongToast
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment :
    BaseFragment(R.layout.fragment_cart),
    GenericListAdapter.GenericListAdapterListener<Cart> {
    private val binding by viewBinding(FragmentCartBinding::bind)
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    private lateinit var cartListAdapter: CartListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartListAdapter = CartListAdapter().apply {
            mListAdapterListener = this@CartFragment
        }
        binding.cartList.adapter = cartListAdapter
        initObservers()
    }

    private fun initObservers() {
        sharedViewModel.userCart.observe(viewLifecycleOwner) { userCart ->
            cartListAdapter.submitList(userCart)
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

    override fun onItemRemoveClicked(
        adapter: GenericListAdapter<Cart>,
        item: Cart
    ) {
        super.onItemRemoveClicked(adapter, item)
        sharedViewModel.removeProductFromCart(item.productId)
    }

    companion object {
        fun newInstance() = CartFragment()
    }
}
