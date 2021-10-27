package com.agah.furkan.androidplayground.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.domain.model.Category
import com.agah.furkan.androidplayground.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentVM @Inject constructor(private val categoryRepository: CategoryRepository) :
    ViewModel() {
    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> get() = _categoryList

    init {
        fetchMainProductCategories()
    }

    private fun fetchMainProductCategories() {
        viewModelScope.launch {
            val categoryList = categoryRepository.fetchMainProductCategories()
            _categoryList.postValue(categoryList)
        }
    }
}
