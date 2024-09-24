package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private  val _categorieState = mutableStateOf(RecipeState())
    val categoriesState :State<RecipeState> = _categorieState
    init {
        fetchCategories()
    }
    private fun fetchCategories(){
        viewModelScope.launch {
            try {
              val reponse = recipeService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = reponse.categories,
                    error = null
                )
            }catch (e : Exception){
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error = "Error Fetching categories.${e.message}"
                )
            }
        }
    }
    data class RecipeState(
        val loading : Boolean = true,
        val list: List<Categorie> = emptyList(),
        val error: String? = null
    )
}