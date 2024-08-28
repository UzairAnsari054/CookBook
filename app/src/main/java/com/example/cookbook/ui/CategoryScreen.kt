package com.example.cookbook.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cookbook.data.model.Category
import com.example.cookbook.presentation.CategoryUiState
import com.example.cookbook.presentation.CategoryViewModel

@Composable
fun CategoryScreen(
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    val categoriesUiState by categoryViewModel.categories.collectAsState()

    when (categoriesUiState) {
        is CategoryUiState.Loading -> {
            LoadingScreen(modifier = Modifier.fillMaxSize())
        }

        is CategoryUiState.Error -> {
            ErrorScreen(
                modifier = Modifier.fillMaxSize(),
                errorMessage = (categoriesUiState as CategoryUiState.Error).errorMessage
            )
        }

        is CategoryUiState.Success -> {
            SuccessScreen(
                modifier = Modifier.fillMaxSize(),
                categories = (categoriesUiState as CategoryUiState.Success).categories
            )
        }
    }

}

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    categories: List<Category>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(categories) { item ->
            CategoryItem(
                modifier = Modifier.fillMaxWidth(),
                categoryThumb = item.strCategoryThumb,
                categoryName = item.strCategory
            )
        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryThumb: String,
    categoryName: String
) {
    Card(
        modifier = modifier
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(categoryThumb)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = categoryName)
        }
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = errorMessage)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = "Loading")
    }
}