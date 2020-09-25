package com.agah.furkan.androidplayground.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.PokemonRepository
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.response.PokemonDetailResponse
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

class PokemonDetailScreenVM @AssistedInject constructor(
    private val pokemonRepository: PokemonRepository,
    @Assisted private val pokemonName: String
) : ViewModel() {
    private val _pokemonDetail = MutableLiveData<ApiResponse<PokemonDetailResponse>>()
    val pokemonDetail: LiveData<ApiResponse<PokemonDetailResponse>> get() = _pokemonDetail

    init {
        viewModelScope.launch {
            val data = pokemonRepository.getPokemonDetail(pokemonName)
            _pokemonDetail.postValue(data)
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(pokemonName: String): PokemonDetailScreenVM
    }
}
