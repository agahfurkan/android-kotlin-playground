package com.agah.furkan.androidplayground.ui.productlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.data.domain.model.Product
import com.agah.furkan.androidplayground.databinding.FragmentProductListBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment :
    BaseFragment(R.layout.fragment_product_list),
    ProductPagingAdapter.ProductListListener {
    private val binding by viewBinding(FragmentProductListBinding::bind)
    private val productListFragmentVM by viewModels<ProductListFragmentVM>()
    private var productPagingAdapter: ProductPagingAdapter? = null
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentProductListBinding.bind(view)
        productPagingAdapter = ProductPagingAdapter().apply {
            mListAdapterListener = this@ProductListFragment
        }
        binding.productList.adapter = productPagingAdapter
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            productListFragmentVM.getProducts.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                productPagingAdapter?.submitData(it)
            }
        }
    }

    override fun onAddToCartClicked(item: Product) {
        sharedViewModel.addProductToCart(item)
    }

    override fun onItemClicked(item: Product) {
        navigate(
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                productId = item.productId
            )
        )
    }
}
