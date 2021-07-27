package com.example.recipeapp.entities

import androidx.room.Entity
import androidx.room.*
import com.example.recipeapp.entities.converter.CategoryListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Category1")
data class Category(
    @PrimaryKey(autoGenerate = true)
                    var id:Int,
    @ColumnInfo(name = "categoryItems")
                       @Expose
                       @SerializedName("categories")
                       @TypeConverters(CategoryListConverter::class)
                       var categorieitems: List<CategoryItems>? = null
)
