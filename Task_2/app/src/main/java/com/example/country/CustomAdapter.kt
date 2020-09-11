package com.example.country

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(var arrayList: ArrayList<model>, var type: Int): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    lateinit var myContext: Context
    var likecount:Int=0
    val GRID = 0
    val LIST = 1

    override fun getItemCount(): Int {
        return arrayList.size
    }
    class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
       val country=itemView.findViewById<TextView>(R.id.country_name)
       val flag=itemView.findViewById<ImageView>(R.id.country_flag)
       val countCapital=itemView.findViewById<TextView>(R.id.capital)
       val prefix=itemView.findViewById<TextView>(R.id.tel_prefix)
        val code2=itemView.findViewById<TextView>(R.id.iso2)
        val num=itemView.findViewById<TextView>(R.id.iso_num)
        val code3=itemView.findViewById<TextView>(R.id.iso3)
       val fips=itemView.findViewById<TextView>(R.id.fips)
        val center=itemView.findViewById<TextView>(R.id.count_center)
       val countRect=itemView.findViewById<TextView>(R.id.count_rect)
        val Lkbtn=itemView.findViewById<ImageButton>(R.id.likeBtn)

    }
    class GridViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val country=itemView.findViewById<TextView>(R.id.grid_country_name)
        val flag=itemView.findViewById<ImageView>(R.id.grid_flag)
        val countCapital=itemView.findViewById<TextView>(R.id.grid_country_capital)
        val prefix=itemView.findViewById<TextView>(R.id.grid_country_telprefix)
        val code2=itemView.findViewById<TextView>(R.id.grid_country_iso2)
        val num=itemView.findViewById<TextView>(R.id.grid_country_isonum)
        val code3=itemView.findViewById<TextView>(R.id.grid_country_iso3)
        val fips=itemView.findViewById<TextView>(R.id.grid_country_fips)
        val center=itemView.findViewById<TextView>(R.id.grid_country_center)
        val countRect=itemView.findViewById<TextView>(R.id.grid_country_rect)
        val Lkbtn=itemView.findViewById<ImageButton>(R.id.grid_likeBtn)
    }

    override fun getItemViewType(position: Int): Int {
        return if (type == GRID) {
            GRID
        } else {
            LIST
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==GRID){
            myContext=parent.context
            val v=LayoutInflater.from(parent.context).inflate(R.layout.gridview, null, false)
            return GridViewHolder(v)
       }
        else{
            myContext=parent.context
            val v=LayoutInflater.from(parent.context).inflate(R.layout.country_card, parent, false)
            return ListViewHolder(v)
        }
    }
    
    //Setting Cards values
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val countries:model=arrayList[position]
        if (holder is ListViewHolder){
            val lVholder=holder as ListViewHolder
            val Capital=myContext.getString(R.string.capital) +countries.capital
            val TelPrefix=myContext.getString(R.string.TelPrefix)+countries.tel_prefix
            val CodeISO2=myContext.getString(R.string.Code_iso_2)+countries.code_iso_2
            val CodeISOnum=myContext.getString(R.string.CodeISONUM)+countries.code_iso_num
            val CodeISO3=myContext.getString(R.string.Code_iso_3)+countries.code_iso_3
            val CodeFIPS=myContext.getString(R.string.CodeFIPS)+countries.code_fips
            val Center=myContext.getString(R.string.center)+countries.centerCode
            val rectangle=myContext.getString(R.string.rectangle)+countries.rectangle_str
            lVholder.flag.setImageResource(countries.country_flag)
            lVholder.country?.text=countries.country_name
            lVholder.countCapital.text= Capital
            lVholder.prefix.text=TelPrefix
            lVholder.code2.text=CodeISO2
            lVholder.num.text=CodeISOnum
            lVholder.code3.text=CodeISO3
            lVholder.fips.text=CodeFIPS
            lVholder.center.text=Center
            lVholder.countRect.text=rectangle
            if(countries.likestatus){
                lVholder.Lkbtn.setBackgroundResource(R.drawable.likedstate)
            }else{
                lVholder.Lkbtn.setBackgroundResource(R.drawable.normal_state)
            }
            lVholder.Lkbtn.setOnClickListener{
                likecount++
                if(likecount%2==0){
                    countries.likestatus=true
                    notifyDataSetChanged()
                }
                else{
                    countries.likestatus=false
                    notifyDataSetChanged()
                    likecount=1
                }
            }
        }
        else{
            val gvHolder=holder as GridViewHolder
            val Capital=myContext.getString(R.string.capital) +countries.capital
            val TelPrefix=myContext.getString(R.string.TelPrefix)+countries.tel_prefix
            val CodeISO2=myContext.getString(R.string.Code_iso_2)+countries.code_iso_2
            val CodeISOnum=myContext.getString(R.string.CodeISONUM)+countries.code_iso_num
            val CodeISO3=myContext.getString(R.string.Code_iso_3)+countries.code_iso_3
            val CodeFIPS=myContext.getString(R.string.CodeFIPS)+countries.code_fips
            val Center=myContext.getString(R.string.center)+countries.centerCode
            val rectangle=myContext.getString(R.string.rectangle)+countries.rectangle_str
            gvHolder.flag.setBackgroundResource(countries.country_flag)
            gvHolder.country?.text=countries.country_name
            gvHolder.countCapital.text= Capital
            gvHolder.prefix.text=TelPrefix
            gvHolder.code2.text=CodeISO2
            gvHolder.num.text=CodeISOnum
            gvHolder.code3.text=CodeISO3
            gvHolder.fips.text=CodeFIPS
            gvHolder.center.text=Center
            gvHolder.countRect.text=rectangle
            if(countries.likestatus){
                gvHolder.Lkbtn.setBackgroundResource(R.drawable.likedstate)
            }else{
                gvHolder.Lkbtn.setBackgroundResource(R.drawable.normal_state)
            }
            gvHolder.Lkbtn.setOnClickListener{
                likecount++
                if(likecount%2==0){
                    countries.likestatus=true
                    notifyDataSetChanged()
                }
                else{
                    countries.likestatus=false
                    notifyDataSetChanged()
                    likecount=1
                }
            }
        }
    }
    fun filterList(filterdNames: ArrayList<model>) {
        arrayList = filterdNames
        notifyDataSetChanged()
    }

}