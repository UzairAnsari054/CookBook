package com.example.cookbook.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.model.Category
import com.example.cookbook.domain.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface CategoryUiState {
    data class Success(val categories: List<Category>) : CategoryUiState
    data class Error(val errorMessage: String) : CategoryUiState
    object Loading : CategoryUiState
}

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    var _categories: MutableStateFlow<CategoryUiState> = MutableStateFlow(CategoryUiState.Loading)
    val categories: StateFlow<CategoryUiState> = _categories.asStateFlow()

    init {
        _categories.value = CategoryUiState.Loading
        viewModelScope.launch {
            _categories.value = try {
                CategoryUiState.Success(
                   categories = getCategoriesUseCase().categories
                )
            } catch (e: Exception) {
                CategoryUiState.Error(errorMessage = e.localizedMessage + "Category Faled Yoo")
            }
        }
    }
}