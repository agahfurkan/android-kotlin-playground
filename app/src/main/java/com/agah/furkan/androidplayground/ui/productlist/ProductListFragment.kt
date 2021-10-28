package com.agah.furkan.androidplayground.ui.productlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.data.domain.model.Product
import com.agah.furkan.androidplayground.databinding.FragmentProductListBinding
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.GenericListAdapter
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.ProductListAdapter
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment :
    BaseFragment(R.layout.fragment_product_list),
    ProductListAdapter.ProductListListener {
    private val binding by viewBinding(FragmentProductListBinding::bind)
    private val productListFragmentVM by viewModels<ProductListFragmentVM>()
    private val args by navArgs<ProductListFragmentArgs>()
    private var productAdapter: ProductListAdapter? = null
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentProductListBinding.bind(view)
        productAdapter = ProductListAdapter().apply {
            mListAdapterListener = this@ProductListFragment
        }
        binding.productList.adapter = productAdapter
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        productListFragmentVM.getProducts(args.categoryId)
    }

    private fun initObservers() {
        productListFragmentVM.productList.observe(viewLifecycleOwner) { productList ->
            productAdapter?.submitList(productList)
        }
    }

    override fun onItemClicked(
        adapter: GenericListAdapter<Product>,
        item: Product
    ) {
        super.onItemClicked(adapter, item)
        navigate(
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                productId = item.productId
            )
        )
    }

    override fun onAddToCartClicked(item: Product) {
        sharedViewModel.addProductToCart(item)
    }
}
