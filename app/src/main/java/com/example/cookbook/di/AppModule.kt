package com.example.cookbook.di

import com.example.cookbook.data.remote.CategoryApiService
import com.example.cookbook.data.respository.CategoryRepository
import com.example.cookbook.data.respository.CategoryResponseImpl
import com.example.cookbook.domain.GetCategoriesUseCase
import com.example.cookbook.domain.GetCategoriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryApiService(retrofit: Retrofit): CategoryApiService {
        return retrofit.create(CategoryApiService::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt {

        @Singleton
        @Binds
        fun provideCategoryRepository(categoryResponseImpl: CategoryResponseImpl): CategoryRepository

        @Singleton
        @Binds
        fun provideGetCategoriesUseCase(getCategoriesUseCaseImpl: GetCategoriesUseCaseImpl): GetCategoriesUseCase

    }
}