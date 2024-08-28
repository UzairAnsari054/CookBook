package com.example.cookbook.data.respository

import com.example.cookbook.data.model.CategoryResponse
import com.example.cookbook.data.remote.CategoryApiService
import javax.inject.Inject

interface CategoryRepository {
    suspend fun getCategories(): CategoryResponse
}

class CategoryResponseImpl @Inject constructor(
    private val categoryApiService: CategoryApiService
) : CategoryRepository {

    override suspend fun getCategories(): CategoryResponse {
        return categoryApiService.getCategories()
    }

}

