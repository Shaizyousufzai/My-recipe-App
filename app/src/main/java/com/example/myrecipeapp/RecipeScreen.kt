package com.example.myrecipeapp

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.Locale.Category

@Composable
fun RecipeScreen(modifier: Modifier = Modifier){
    val recipeViewModel :MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState
    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error!= null->{
                Text(text ="Error Occured" )
            }
            else->{
                CategoryScreen(categories = viewState.list)
            }
        }
    }
}
@Composable
fun CategoryScreen(categories:List<Categorie> ){
    LazyVerticalGrid(GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()){
        items(categories){
                categorie ->
            CategoryItem(categorie = categorie)
        }
    }
}
@Composable
fun CategoryItem(categorie: Categorie){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(categorie.strCategoryThumb), // use rememberImagePainter instead of rememberAsyncImagePainter
            contentDescription = null, // provide a contentDescription for your image
            modifier = Modifier.fillMaxSize().aspectRatio(1f)

        )

        Text(
            text = categorie.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}