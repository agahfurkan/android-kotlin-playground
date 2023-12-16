package com.agah.furkan.x

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.x.model.StoreListModel
import com.agah.furkan.x.model.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                isLoading = false,
                storeList = listOf(
                    StoreListModel(1, "https://www.google.com/ur"),
                    StoreListModel(2, "https://www.google.com/ur"),
                    StoreListModel(3, "https://www.google.com/ur"),
                    StoreListModel(4, "https://www.google.com/ur"),
                    StoreListModel(5, "https://www.google.com/ur"),
                    StoreListModel(6, "https://www.google.com/ur"),
                    StoreListModel(7, "https://www.google.com/ur"),
                )

            )
        }
    }

    fun onSearchTextChanged(text: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(searchText = text) }
        }
    }
}
