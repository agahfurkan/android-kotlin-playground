package com.agah.furkan.androidplayground.ui.main.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.callback.IListAdapterListener
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.response.CategoryResponse
import com.agah.furkan.androidplayground.databinding.FragmentDiscoverBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.MainCategoryListAdapter
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.main.MainFragmentDirections
import com.agah.furkan.androidplayground.ui.main.MainFragmentVM
import javax.inject.Inject

class DiscoverFragment : BaseFragment(), InjectableFragment,
    IListAdapterListener<CategoryResponse.Category> {
    private var _binding: FragmentDiscoverBinding? = null
    private val binding: FragmentDiscoverBinding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val mainFragmentVM by viewModels<MainFragmentVM>(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { factory })
    private var categoryListAdapter: MainCategoryListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryListAdapter = MainCategoryListAdapter(this)
        binding.discoverCategoryList.adapter = categoryListAdapter
        initObservers()
    }

    private fun initObservers() {
        mainFragmentVM.categoryList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiSuccessResponse -> {
                    categoryListAdapter?.submitList(it.data.categoryList)
                }
                is ApiErrorResponse -> {

                }
            }
        }
    }

    override fun listItemClicked(item: CategoryResponse.Category) {
        super.listItemClicked(item)
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToProductListFragment(
                categoryId = item.categoryId
            )
        )
    }
}