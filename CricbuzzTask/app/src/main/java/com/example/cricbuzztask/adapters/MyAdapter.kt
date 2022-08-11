package com.example.cricbuzztask.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzztask.MainActivity
import com.example.cricbuzztask.databinding.ListItemBinding
import com.example.cricbuzztask.model.Restaurant.ModelItem

class MyAdapter: RecyclerView.Adapter<MyAdapter.UserHolder>() {

    private var restList:List<ModelItem> = listOf()
    private lateinit var binding: ListItemBinding

    inner class UserHolder(var binding: ListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        binding=ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        Log.d("Main Adapter",restList[position].name)
        holder.binding.restID.text=restList[position].id.toString()
        holder.binding.name.text=restList[position].name
        holder.binding.cuisine.text=restList[position].cuisineType
        holder.binding.address.text=restList[position].address

    }

    override fun getItemCount(): Int {
        return restList.size
    }

    fun setData(list:List<ModelItem>){
        restList=list
        notifyDataSetChanged()
    }

}