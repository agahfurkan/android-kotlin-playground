package com.agah.furkan.androidplayground.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.domain.model.request.UseCaseParams
import com.agah.furkan.androidplayground.domain.usecase.GetMainProductCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenVM @Inject constructor(private val getMainProductCategoryUseCase: GetMainProductCategoryUseCase) :
    ViewModel() {
    private val _categoryList =
        MutableStateFlow<GetMainProductCategoryUseCase.UiState>(GetMainProductCategoryUseCase.UiState.Loading)
    val categoryList: StateFlow<GetMainProductCategoryUseCase.UiState> get() = _categoryList

    init {
        fetchMainProductCategories()
    }

    private fun fetchMainProductCategories() {
        viewModelScope.launch {
            getMainProductCategoryUseCase(UseCaseParams.None).collect {
                _categoryList.emit(it)
            }
        }
    }
}
