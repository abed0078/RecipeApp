 package com.example.recipeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ItemRvSubCategoryBinding
import com.example.recipeapp.entities.MealsItems

 class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {
    var listener: SubCategoryAdapter.OnItemClickListener? = null
    var ctx:Context?=null
    var arrSubCategory=ArrayList<MealsItems>()
   inner class RecipeViewHolder(view: View):RecyclerView.ViewHolder(view){
       val binding = ItemRvSubCategoryBinding.bind(view)
   }

fun setData(arrData:List<MealsItems>){
    arrSubCategory=arrData as ArrayList<MealsItems>
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx=parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sub_category,parent,false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
            with(holder) {
            binding.tvDishName2.text = arrSubCategory[position].strMeal
            Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb).into(binding.imageView2)

                holder.itemView.rootView.setOnClickListener {
                    listener!!.onClicked(arrSubCategory[position].idMeal)
                }
        }
    }

    override fun getItemCount(): Int {
       return arrSubCategory.size
    }
    fun setClickListener(listener1: SubCategoryAdapter.OnItemClickListener){
        listener=listener1
    }
    interface OnItemClickListener{
        fun onClicked(id: String)
    }
}