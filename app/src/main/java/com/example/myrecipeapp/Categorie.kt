package com.example.myrecipeapp

data class Categorie(val idCategory :String,
                    val strCategory: String,
                    val strCategoryThumb:String,
                    val strCategoryDescription :String)

data class CategoriesResponse(val categories : List<Categorie>)
