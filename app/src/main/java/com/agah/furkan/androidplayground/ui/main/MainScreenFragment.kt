package com.agah.furkan.androidplayground.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.androidplayground.data.local.model.PokemonCache
import com.agah.furkan.androidplayground.databinding.FragmentMainScreenBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.ItemClickListener
import javax.inject.Inject

class MainScreenFragment : BaseFragment(), InjectableFragment, ItemClickListener<PokemonCache> {
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    private fun test() {
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val mainScreenVM: MainScreenVM by viewModels { factory }
    private val pokemonAdapter: MainScreenPokemonAdapter by lazy {
        MainScreenPokemonAdapter(this)
    }
    private lateinit var pokemonListLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonListLayoutManager = LinearLayoutManager(context)
        binding.mainScreenPokemonList.adapter = pokemonAdapter
        binding.mainScreenPokemonList.layoutManager = pokemonListLayoutManager
        mainScreenVM.pokemonList.observe(viewLifecycleOwner, Observer { pokemonList ->
            if (!pokemonList.isNullOrEmpty()) {
                pokemonAdapter.submitList(pokemonList)
            }
        })
        binding.mainScreenPokemonList.addOnScrollListener(pokemonListScrollListener)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private val pokemonListScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = pokemonListLayoutManager.childCount
            val totalItemCount = pokemonListLayoutManager.itemCount
            val firstVisibleItemPosition =
                pokemonListLayoutManager.findFirstVisibleItemPosition()
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isValidFirstItem = firstVisibleItemPosition >= 0
            val shouldLoadMore = isValidFirstItem && isAtLastItem
            if (shouldLoadMore && !mainScreenVM.isRequestActive) {
                if (mainScreenVM.pokemonListTotalCount != null && totalItemCount < mainScreenVM.pokemonListTotalCount!!) {
                    mainScreenVM.getPokemonDataFromNetwork(totalItemCount)
                }
            }
        }
    }

    override fun onItemClicked(item: PokemonCache) {
        findNavController().navigate(
            MainScreenFragmentDirections.actionMainScreenFragmentToPokemonDetailScreenFragment(
                item.name
            )
        )
    }
}
