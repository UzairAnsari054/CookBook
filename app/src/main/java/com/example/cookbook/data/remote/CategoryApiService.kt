package com.example.cookbook.data.remote

import com.example.cookbook.data.model.CategoryResponse
import retrofit2.http.GET

interface CategoryApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse
}

