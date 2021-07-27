package com.example.recipeapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.ActivityDetailBinding
import com.example.recipeapp.databinding.ActivityHomeBinding
import com.example.recipeapp.entities.MealResponse
import com.example.recipeapp.interfaces.GetDataService
import com.example.recipeapp.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    var youtubeLink = ""
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var id = intent.getStringExtra("id")
        getSpecificItem(id!!)
        binding.imgToolbarBtnBack.setOnClickListener { finish() }
        binding.btnYoutube.setOnClickListener {
            val uri:Uri=
                Uri.parse(youtubeLink)
            val intent=Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)

        }
    }

    fun getSpecificItem(id:String) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getSpecificItem(id)
        call.enqueue(object : Callback<MealResponse> {

            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {

                Glide.with(this@DetailActivity).load(response.body()!!.meals[0].strMealThumb)
                    .into(binding.imgItem)

                binding.tvCategory.text = response.body()!!.meals[0].strMeal
                var ingredient =
                    "${response.body()!!.meals[0].strIngredient1}      ${response.body()!!.meals[0].strMeasure1}\n" +
                            "${response.body()!!.meals[0].strIngredient2}      ${response.body()!!.meals[0].strMeasure2}\n" +
                            "${response.body()!!.meals[0].strIngredient3}      ${response.body()!!.meals[0].strMeasure3}\n" +
                            "${response.body()!!.meals[0].strIngredient4}      ${response.body()!!.meals[0].strMeasure4}\n" +
                            "${response.body()!!.meals[0].strIngredient5}      ${response.body()!!.meals[0].strMeasure5}\n" +
                            "${response.body()!!.meals[0].strIngredient6}      ${response.body()!!.meals[0].strMeasure6}\n" +
                            "${response.body()!!.meals[0].strIngredient7}      ${response.body()!!.meals[0].strMeasure7}\n" +
                            "${response.body()!!.meals[0].strIngredient8}      ${response.body()!!.meals[0].strMeasure8}\n" +
                            "${response.body()!!.meals[0].strIngredient9}      ${response.body()!!.meals[0].strMeasure9}\n" +
                            "${response.body()!!.meals[0].strIngredient10}      ${response.body()!!.meals[0].strMeasure10}\n" +
                            "${response.body()!!.meals[0].strIngredient11}      ${response.body()!!.meals[0].strMeasure11}\n" +
                            "${response.body()!!.meals[0].strIngredient12}      ${response.body()!!.meals[0].strMeasure12}\n" +
                            "${response.body()!!.meals[0].strIngredient13}      ${response.body()!!.meals[0].strMeasure13}\n" +
                            "${response.body()!!.meals[0].strIngredient14}      ${response.body()!!.meals[0].strMeasure14}\n" +
                            "${response.body()!!.meals[0].strIngredient15}      ${response.body()!!.meals[0].strMeasure15}\n"

                binding.tvIngredients.text = ingredient
                binding.tvInstructions.text = response.body()!!.meals[0].strInstructions
                if (response.body()!!.meals[0].strsource != null) {
                    youtubeLink = response.body()!!.meals[0].strsource
                } else {
                    binding.btnYoutube.visibility = View.GONE
                }

            }


            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

}