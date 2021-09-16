package com.agah.furkan.androidplayground.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.CategoryRepository
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.response.CategoryResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentVM @Inject constructor(private val categoryRepository: CategoryRepository) :
    ViewModel() {
    private val _categoryList = MutableLiveData<ApiResponse<CategoryResponse>>()
    val categoryList: LiveData<ApiResponse<CategoryResponse>> get() = _categoryList

    init {
        fetchMainProductCategories()
    }

    fun fetchMainProductCategories() {
        viewModelScope.launch {
            val response = categoryRepository.fetchMainProductCategories()
            _categoryList.postValue(response)
        }
    }
}
