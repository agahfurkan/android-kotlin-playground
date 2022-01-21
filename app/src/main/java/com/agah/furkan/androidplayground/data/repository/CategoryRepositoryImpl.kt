package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.mapper.toDomainModel
import com.agah.furkan.androidplayground.data.remote.service.CategoryService
import com.agah.furkan.androidplayground.domain.ErrorMapper
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.result.Category
import com.agah.furkan.androidplayground.domain.repository.CategoryRepository
import com.agah.furkan.androidplayground.domain.util.suspendCall
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

    override suspend fun fetchMainProductCategories(): Result<List<Category>> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            call = { categoryService.fetchCategories() },
            map = { response -> response.categoryList.map { it.toDomainModel() } }
        )
}
