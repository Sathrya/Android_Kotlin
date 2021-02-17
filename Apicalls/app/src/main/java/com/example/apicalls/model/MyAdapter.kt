package com.example.apicalls.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalls.R

class MyAdapter(private var userList :MutableList<Data>): RecyclerView.Adapter<MyAdapter.UserHolder>() {
    //var userList :List<Data> = listOf()
    private lateinit var mcontext: Context

    class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var uname:TextView=itemView.findViewById(R.id.name)
        var uid: TextView =itemView.findViewById(R.id.uid)
        var uemail: TextView=itemView.findViewById(R.id.email)
        var gender:TextView=itemView.findViewById(R.id.gender)
        var status:TextView=itemView.findViewById(R.id.status)
        var create:TextView=itemView.findViewById(R.id.createdAt)
        var update:TextView=itemView.findViewById(R.id.updatedAt)
        val image:ImageView=itemView.findViewById(R.id.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        mcontext=parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.uname.text= userList[position].name
        holder.uid.text= userList[position].id.toString()
        holder.uemail.text= userList[position].email
        holder.gender.text= userList[position].gender
        holder.status.text= userList[position].status
        holder.create.text= userList[position].createdAt
        holder.update.text= userList[position].updatedAt
        if (userList[position].gender=="Male"){
            holder.image.setImageResource(R.drawable.male_icon)
        }
        else{
            holder.image.setImageResource(R.drawable.female_icon)
        }
    }
    fun appendusers(userList: MutableList<Data>){
        this.userList.addAll(userList)
        notifyItemRangeInserted(
            this.userList.size,
            userList.size+1
        )
    }
}