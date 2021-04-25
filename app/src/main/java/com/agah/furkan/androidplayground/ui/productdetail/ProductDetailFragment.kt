package com.agah.furkan.androidplayground.ui.productdetail

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.databinding.FragmentProductDetailBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.showLongToast
import com.bumptech.glide.Glide
import javax.inject.Inject

class ProductDetailFragment : BaseFragment(), InjectableFragment {
    private var _binding: FragmentProductDetailBinding? = null
    val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val productDetailFragmentVM by viewModels<ProductDetailFragmentVM> { factory }
    private val args by navArgs<ProductDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        productDetailFragmentVM.getProductDetail(args.productId)
    }

    private fun initObservers() {
        productDetailFragmentVM.productDetail.observe(viewLifecycleOwner) { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    if (apiResponse.data.isSuccess) {
                        with(apiResponse.data.productDetail!!) {
                            Glide.with(this@ProductDetailFragment)
                                .load(R.drawable.dummy_product_image)
                                .placeholder(R.drawable.placeholder_image)
                                .into(binding.productDetailImage)
                            binding.productDetailName.text = productName
                            binding.productDetailDescription.text = productDescription
                            binding.productDetailPrice.text = price.toString()
                            if (discount != 0.0) {
                                binding.productDetailPrice.paintFlags =
                                    binding.productDetailPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                                binding.productDetailDiscountedPrice.visibility = View.VISIBLE
                                binding.productDetailDiscountedPrice.text =
                                    price.minus(price.times(discount).div(100)).toString()
                            } else {
                                binding.productDetailPrice.paintFlags =
                                    binding.productDetailPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                                binding.productDetailDiscountedPrice.visibility = View.GONE
                            }
                        }
                    } else {
                        showLongToast(apiResponse.data.message.toString())
                    }
                }
                is ApiErrorResponse -> {

                }
            }
        }
    }
}