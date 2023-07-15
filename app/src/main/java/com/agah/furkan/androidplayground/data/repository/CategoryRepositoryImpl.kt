package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.mapper.toDomainModel
import com.agah.furkan.androidplayground.data.remote.service.CategoryService
import com.agah.furkan.androidplayground.domain.model.result.Category
import com.agah.furkan.androidplayground.domain.repository.CategoryRepository
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

    override suspend fun fetchMainProductCategories(): com.agah.furkan.data.model.Result<List<Category>> =
        com.agah.furkan.data.suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response.categoryList.map { it.toDomainModel() } }
        ) { categoryService.fetchCategories() }
}
