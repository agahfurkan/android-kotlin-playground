package com.agah.furkan.androidplayground.ui.main.child

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.data.domain.model.Category
import com.agah.furkan.androidplayground.databinding.FragmentCategoryBinding
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.GenericListAdapter
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.MainCategoryListAdapter
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.main.MainFragmentDirections
import com.agah.furkan.androidplayground.ui.main.MainFragmentVM
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment :
    BaseFragment(R.layout.fragment_category),
    GenericListAdapter.GenericListAdapterListener<Category> {
    private val binding by viewBinding(FragmentCategoryBinding::bind)
    private val mainFragmentVM by viewModels<MainFragmentVM>(
        ownerProducer = { requireParentFragment() }
    )
    private lateinit var categoryListAdapter: MainCategoryListAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryListAdapter = MainCategoryListAdapter().apply {
            mListAdapterListener = this@CategoryFragment
        }
        binding.discoverCategoryList.adapter = categoryListAdapter
        initObservers()
    }

    private fun initObservers() {
        mainFragmentVM.categoryList.observe(viewLifecycleOwner) { categoryList ->
            categoryListAdapter.submitList(categoryList)
        }
    }

    override fun onItemClicked(
        adapter: GenericListAdapter<Category>,
        item: Category
    ) {
        navigate(
            MainFragmentDirections.actionMainFragmentToProductListFragment(
                categoryId = item.categoryId
            )
        )
    }

    companion object {
        fun newInstance() = CategoryFragment()
    }
}
