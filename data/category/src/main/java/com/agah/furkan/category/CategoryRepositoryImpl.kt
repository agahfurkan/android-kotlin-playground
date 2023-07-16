package com.agah.furkan.category

import com.agah.furkan.category.remote.CategoryService
import com.agah.furkan.category.remote.model.response.CategoryResponse
import com.agah.furkan.data.ErrorMapper
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CategoryRepositoryImpl(
    private val categoryService: CategoryService,
    private val errorMapper: ErrorMapper,
    private val coroutineContext: CoroutineContext
) : CategoryRepository {
    @Inject
    constructor(
        categoryService: CategoryService,
        errorMapper: ErrorMapper
    ) : this(categoryService, errorMapper, Dispatchers.IO)

    override suspend fun fetchMainProductCategories(): com.agah.furkan.data.model.Result<List<CategoryResponse.Category>> =
        com.agah.furkan.data.suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response.categoryList }
        ) { categoryService.fetchCategories() }
}
