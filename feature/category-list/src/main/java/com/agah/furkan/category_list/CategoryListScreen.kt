package com.agah.furkan.category_list

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
import com.agah.furkan.category.remote.model.response.CategoryResponse
import com.agah.furkan.ui.component.LoadingState
import com.agah.furkan.ui.component.PlaceHolderImage
import com.agah.furkan.ui.theme.AppTheme
import com.agah.furkan.ui.theme.seed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListScreen(
    viewModel: CategoryListViewModel = hiltViewModel(),
    onCategoryClicked: (CategoryResponse.Category) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state = viewModel.categoryList.collectAsState()
    val stateValue = state.value

    AppTheme {
        Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = seed)
            )
        }) { padding ->
            Column(Modifier.padding(padding)) {
                when (stateValue) {
                    is CategoryListUiState.Success -> {
                        CategoryListSuccessState(stateValue, onCategoryClicked)
                    }

                    is CategoryListUiState.Error -> {
                        LaunchedEffect(snackbarHostState) {
                            snackbarHostState.showSnackbar(
                                message = stateValue.errorMessage,
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
fun CategoryListSuccessState(
    state: CategoryListUiState.Success,
    onCategoryClicked: (CategoryResponse.Category) -> Unit
) {
    LazyVerticalGrid(modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(3),
        content = {
            items(state.data.size) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onCategoryClicked(
                            state.data[it]
                        )
                    }) {
                    PlaceHolderImage()
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.data[it].categoryName,
                        textAlign = TextAlign.Center
                    )
                }
            }
        })
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        LoadingState(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
@Preview
fun CategoryListSuccessStatePreview(
    @PreviewParameter(CategoryPreviewParameterProvider::class) categoryList: List<CategoryResponse.Category>
) {
    CategoryListSuccessState(
        CategoryListUiState.Success(data = categoryList)
    ) {

    }
}

@Composable
@Preview
fun LoadingPreview() {
    Loading()
}
