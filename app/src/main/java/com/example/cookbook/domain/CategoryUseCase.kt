package com.example.cookbook.domain

import com.example.cookbook.data.model.CategoryResponse
import com.example.cookbook.data.respository.CategoryRepository
import javax.inject.Inject

interface GetCategoriesUseCase {
    suspend operator fun invoke(): CategoryResponse
}

class GetCategoriesUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository
) : GetCategoriesUseCase {

    override suspend fun invoke(): CategoryResponse {
        return categoryRepository.getCategories()
    }

}