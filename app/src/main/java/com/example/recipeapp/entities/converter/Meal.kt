package com.example.recipeapp.entities.converter

import androidx.room.*
import com.example.recipeapp.entities.MealsItems
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Meal")
class Meal(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "meals")
    @Expose
    @SerializedName("meals")
    @TypeConverters(MealListConverter::class)
    var mealsItems: List<MealsItems>? = null


)