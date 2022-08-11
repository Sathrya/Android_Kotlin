package com.example.cricbuzztask.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzztask.databinding.MenuItemBinding
import com.example.cricbuzztask.model.Menu.MenuItems
import com.example.cricbuzztask.model.Restaurant.ModelItem

class MenuAdapter: RecyclerView.Adapter<MenuAdapter.MenuHolder>() {

    private var menuList:List<MenuItems> = listOf()
    private lateinit var binding: MenuItemBinding

    inner class MenuHolder(var binding: MenuItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        binding= MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MenuHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        Log.d("Menu Adapter",menuList[position].name)
        holder.binding.name.text=menuList[position].name
        holder.binding.price.text=menuList[position].price
        holder.binding.description.text=menuList[position].description


    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun setData(list:List<MenuItems>){
        menuList=list
        notifyDataSetChanged()
    }

}