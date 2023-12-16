package com.agah.furkan.x.model

data class UiState(
    val isLoading: Boolean = true,
    val storeList: List<StoreListModel> = listOf(),
    val searchText: String = ""
)
