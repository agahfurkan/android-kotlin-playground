package com.agah.furkan.androidplayground.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agah.furkan.androidplayground.databinding.FragmentPokemonDetailScreenBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment

class PokemonDetailScreenFragment : BaseFragment(), InjectableFragment {
    private var _binding: FragmentPokemonDetailScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
}