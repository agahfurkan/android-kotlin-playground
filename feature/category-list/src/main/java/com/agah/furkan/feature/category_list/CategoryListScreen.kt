package com.agah.furkan.feature.category_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.core.ui.theme.AppTheme
import com.agah.furkan.core.ui.theme.seed
import com.agah.furkan.feature.category_list.model.Category
import com.agah.furkan.feature.category_list.model.CategoryListUiState
import com.agah.furkan.ui.components.LoadingState
import com.agah.furkan.ui.components.PlaceHolderImage

@Composable
internal fun CategoryListRoute(
    viewModel: CategoryListViewModel = hiltViewModel(),
    onCategoryClicked: (categoryId: Long) -> Unit
) {
    val state = viewModel.categoryList.collectAsState()
    val stateValue = state.value

    CategoryListScreen(stateValue, onCategoryClicked)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CategoryListScreen(
    state: CategoryListUiState,
    onCategoryClicked: (categoryId: Long) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    AppTheme {
        Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = seed)
            )
        }) { padding ->
            Column(Modifier.padding(padding)) {
                when (state) {
                    is CategoryListUiState.Success -> {
                        CategoryListSuccessState(state, onCategoryClicked)
                    }

                    is CategoryListUiState.Error -> {
                        LaunchedEffect(snackbarHostState) {
                            snackbarHostState.showSnackbar(
                                message = state.errorMessage,
                                duration = SnackbarDuration.Indefinite,
                            )
                        }
                    }

                    CategoryListUiState.Loading -> {
                        Loading()
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryListSuccessState(
    state: CategoryListUiState.Success,
    onCategoryClicked: (categoryId: Long) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(3),
        content = {
            items(state.data.size) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCategoryClicked(
                                state.data[it].categoryId
                            )
                        }
                ) {
                    PlaceHolderImage()
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.data[it].categoryName,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        LoadingState(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
@Preview
private fun CategoryListScreenPreview() {
    CategoryListScreen(
        state = CategoryListUiState.Success(
            listOf(
                Category(
                    categoryId = 1,
                    categoryName = "categoryName"
                ),
                Category(
                    categoryId = 2,
                    categoryName = "categoryName"
                ),
                Category(
                    categoryId = 3,
                    categoryName = "categoryName"
                ),
                Category(
                    categoryId = 4,
                    categoryName = "categoryName"
                ),
                Category(
                    categoryId = 5,
                    categoryName = "categoryName"
                )
            )
        ),
        onCategoryClicked = {}
    )
}

@Composable
@Preview
private fun CategoryListSuccessStatePreview(
    @PreviewParameter(CategoryPreviewParameterProvider::class) categoryList: List<Category>
) {
    CategoryListSuccessState(
        CategoryListUiState.Success(data = categoryList)
    ) {
    }
}

@Composable
@Preview
private fun LoadingPreview() {
    Loading()
}
