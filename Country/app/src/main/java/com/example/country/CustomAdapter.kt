package com.example.country

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(var arrayList: ArrayList<model>):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
       val country=itemView.findViewById<TextView>(R.id.country_name)
        val country_card=itemView.findViewById<CardView>(R.id.card)
       val flag=itemView.findViewById<ImageView>(R.id.country_flag)
       val count_capital=itemView.findViewById<TextView>(R.id.capital)
       val prefix=itemView.findViewById<TextView>(R.id.tel_prefix)
         val code_2=itemView.findViewById<TextView>(R.id.iso2)
        val num=itemView.findViewById<TextView>(R.id.iso_num)
        val code_3=itemView.findViewById<TextView>(R.id.iso3)
       val fips=itemView.findViewById<TextView>(R.id.fips)
        val center=itemView.findViewById<TextView>(R.id.count_center)
       val count_rect=itemView.findViewById<TextView>(R.id.count_rect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent?.context).inflate(R.layout.country_card, parent, false)
        return ViewHolder(v)
    }

    //Setting Cards values
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val countries:model=arrayList[position]
        holder.flag.setImageResource(countries.country_flag)
       holder.country?.text=countries.country_name
        holder.count_capital.text= "Capital:  "+countries.capital
        holder.prefix.text="Tel Prefix:  "+countries.tel_prefix
       holder.code_2.text="Code ISO 2:  "+countries.code_iso_2
        holder.num.text="Code ISO Num:  "+countries.code_iso_num
       holder.code_3.text="Code ISO 3:  "+countries.code_iso_3
        holder.fips.text="Code FIPS:  "+countries.code_fips
        holder.center.text="Center:  "+countries.centerCode
        holder.count_rect.text="Rectangle:  "+countries.rectangle_str
    }
    override fun getItemCount(): Int {
       return arrayList.size
    }


    fun filterList(filterdNames: ArrayList<model>) {
        arrayList = filterdNames
        notifyDataSetChanged()
    }

}