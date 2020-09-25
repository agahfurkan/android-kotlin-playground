package com.agah.furkan.androidplayground.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.databinding.FragmentPokemonDetailScreenBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import javax.inject.Inject

class PokemonDetailScreenFragment : BaseFragment(), InjectableFragment {
    private var _binding: FragmentPokemonDetailScreenBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<PokemonDetailScreenFragmentArgs>()

    @Inject
    lateinit var factory: PokemonDetailScreenVM.Factory
    private val pokemonDetailScreenVM by lazy {
        factory.create(args.pokemonName)
    }
    private val adapter by lazy {
        PokemonAbilityListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemonAbilityList.adapter = adapter
        pokemonDetailScreenVM.pokemonDetail.observe(viewLifecycleOwner, Observer {
            if (it is ApiSuccessResponse) {
                with(it.data) {
                    binding.pokemonId.text = id.toString()
                    binding.pokemonName.text = args.pokemonName
                    adapter.submitList(abilities)
                }
            }
        })
    }
}
