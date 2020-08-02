package com.agah.furkan.androidplayground.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.PokemonRepository
import com.agah.furkan.androidplayground.data.web.model.PokemonResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenVM @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {
    private val _pokemonList = MutableLiveData<PokemonResponse>()
    val pokemonList: LiveData<PokemonResponse> get() = _pokemonList

    init {
        viewModelScope.launch {
            try {
                val data = pokemonRepository.getPokemonList(0, 50)
                _pokemonList.postValue(data)
            } catch (e: Exception) {
                Log.d("mainScreen", e.localizedMessage.toString())
            }
        }
    }
}