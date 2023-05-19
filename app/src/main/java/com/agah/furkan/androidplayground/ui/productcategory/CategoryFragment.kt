package com.agah.furkan.androidplayground.ui.productcategory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentCategoryBinding
import com.agah.furkan.androidplayground.domain.model.result.Category
import com.agah.furkan.androidplayground.domain.usecase.GetMainProductCategoryUseCase
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.main.MainFragmentDirections
import com.agah.furkan.androidplayground.ui.main.MainFragmentVM
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CategoryFragment : BaseFragment(R.layout.fragment_category), ProductCategoryListCallback {
    private val binding by viewBinding(FragmentCategoryBinding::bind)
    private val mainFragmentVM by viewModels<MainFragmentVM>(
        ownerProducer = { requireParentFragment() }
    )
    private lateinit var categoryListAdapter: ProductCategoryListAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryListAdapter = ProductCategoryListAdapter(this)
        binding.discoverCategoryList.adapter = categoryListAdapter
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainFragmentVM.categoryList.flowWithLifecycle(lifecycle = viewLifecycleOwner.lifecycle)
                .collect { state ->
                    when (state) {
                        is GetMainProductCategoryUseCase.UiState.Success -> {
                            categoryListAdapter.submitList(state.categoryList)
                        }
                        is GetMainProductCategoryUseCase.UiState.Loading -> {
                            Timber.i(state.toString())
                        }
                        is GetMainProductCategoryUseCase.UiState.Failure -> {
                            Timber.i(state.toString())
                        }
                    }
                }
        }
    }

    companion object {
        fun newInstance() = CategoryFragment()
    }

    override fun onItemClicked(item: Category) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToProductListFragment(
                categoryId = item.categoryId
            )
        )
    }
}
