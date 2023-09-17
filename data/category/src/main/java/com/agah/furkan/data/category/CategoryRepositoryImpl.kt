package com.agah.furkan.data.category

import com.agah.furkan.core.data.ErrorMapper
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.data.suspendCall
import com.agah.furkan.data.category.model.CategoryDomainModel
import com.agah.furkan.data.category.model.asDomainModel
import com.agah.furkan.data.category.remote.CategoryService
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

    override suspend fun fetchMainProductCategories(): Result<List<CategoryDomainModel>> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response.categoryList.map { it.asDomainModel() } }
        ) { categoryService.fetchCategories() }
}
