package com.agah.furkan.androidplayground.ui.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.databinding.FragmentCartBinding
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.result.Cart
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.showLongToast
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment :
    BaseFragment(R.layout.fragment_cart),
    CartListCallback {
    private val binding by viewBinding(FragmentCartBinding::bind)
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    private lateinit var cartListAdapter: CartListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartListAdapter = CartListAdapter(this)
        binding.cartList.adapter = cartListAdapter
        initObservers()
    }

    private fun initObservers() {
        sharedViewModel.userCart.observe(viewLifecycleOwner) { userCart ->
            cartListAdapter.submitList(userCart)
        }
        sharedViewModel.removeProductFromCart.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> showLongToast(result.data)
                is Result.Failure -> {
                }
            }
        }
    }

    companion object {
        fun newInstance() = CartFragment()
    }

    override fun onRemoveClick(item: Cart) {
        sharedViewModel.removeProductFromCart(item.productId)
    }
}
