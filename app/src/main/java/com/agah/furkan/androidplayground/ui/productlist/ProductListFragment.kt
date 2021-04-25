package com.agah.furkan.androidplayground.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.response.ProductResponse
import com.agah.furkan.androidplayground.databinding.FragmentProductListBinding
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.ProductListAdapter
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : BaseFragment(),
    ProductListAdapter.ProductAdapterListener {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private val productListFragmentVM by viewModels<ProductListFragmentVM>()
    private val args by navArgs<ProductListFragmentArgs>()
    private var productAdapter: ProductListAdapter? = null
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = ProductListAdapter(this)
        binding.productList.adapter = productAdapter
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        productListFragmentVM.getProducts(args.categoryId)
    }

    private fun initObservers() {
        productListFragmentVM.productList.observe(viewLifecycleOwner) { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    productAdapter?.submitList(apiResponse.data.productList)
                }
                is ApiErrorResponse -> {

                }
            }
        }
    }

    override fun onProductItemClicked(item: ProductResponse.Product) {
        findNavController().navigate(
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                productId = item.productId
            )
        )
    }

    override fun onAddToCartClicked(item: ProductResponse.Product) {

    }
}