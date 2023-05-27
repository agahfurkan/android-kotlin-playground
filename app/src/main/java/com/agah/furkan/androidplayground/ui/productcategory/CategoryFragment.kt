package com.agah.furkan.androidplayground.ui.productcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.domain.model.result.Category
import com.agah.furkan.androidplayground.domain.usecase.GetMainProductCategoryUseCase
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.component.PlaceHolderImage
import com.agah.furkan.androidplayground.ui.component.LoadingState
import com.agah.furkan.androidplayground.ui.main.MainFragmentDirections
import com.agah.furkan.androidplayground.ui.main.MainFragmentVM
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.ui.theme.seed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment(null) {
    private val viewModel by viewModels<MainFragmentVM>(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state = viewModel.categoryList.collectAsState()
                val stateValue = state.value
                CategoryScreen(stateValue) { category ->
                    findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToProductListFragment(
                            category.categoryId
                        )
                    )
                }
            }
        }
    }


    companion object {
        fun newInstance() = CategoryFragment()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    state: GetMainProductCategoryUseCase.UiState,
    onCategoryClicked: (Category) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    AppTheme {
        Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = seed)
            )
        }) { padding ->
            Column(Modifier.padding(padding)) {
                when (state) {
                    is GetMainProductCategoryUseCase.UiState.Success -> {
                        CategoryListSuccessState(state, onCategoryClicked)
                    }

                    is GetMainProductCategoryUseCase.UiState.Failure -> {
                        LaunchedEffect(snackbarHostState) {
                            snackbarHostState.showSnackbar(
                                message = state.failureMessage,
                                duration = SnackbarDuration.Indefinite,
                            )
                        }
                    }

                    GetMainProductCategoryUseCase.UiState.Loading -> {
                        Loading()
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryListSuccessState(
    state: GetMainProductCategoryUseCase.UiState.Success,
    onCategoryClicked: (Category) -> Unit
) {
    LazyVerticalGrid(modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(3),
        content = {
            items(state.categoryList.size) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onCategoryClicked(
                            state.categoryList[it]
                        )
                    }) {
                    PlaceHolderImage()
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Category $it",
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
    @PreviewParameter(CategoryPreviewParameterProvider::class) categoryList: List<Category>
) {
    CategoryListSuccessState(
        GetMainProductCategoryUseCase.UiState.Success(
            categoryList = categoryList
        )
    ) {

    }
}

@Composable
@Preview
fun LoadingPreview() {
    Loading()
}


@Composable
@Preview
fun CategoryScreenPreview() {
    CategoryScreen(
        GetMainProductCategoryUseCase.UiState.Loading
    ) {

    }
}
