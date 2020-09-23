package com.example.myapplication


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Data

class MyAdapter():RecyclerView.Adapter<MyAdapter.UserHolder>() {
    var userList : List<Data> = listOf()
    lateinit var mcontext:Context

    class UserHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var uname=itemView.findViewById<TextView>(R.id.name)
        var uid=itemView.findViewById<TextView>(R.id.uid)
        var uemail=itemView.findViewById<TextView>(R.id.email)
        var gender=itemView.findViewById<TextView>(R.id.gender)
        var status=itemView.findViewById<TextView>(R.id.status)
        var create=itemView.findViewById<TextView>(R.id.createdAt)
        var update=itemView.findViewById<TextView>(R.id.updatedAt)
        val image=itemView.findViewById<ImageView>(R.id.icon)

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
         holder.uname.text= userList.get(position).name
         holder.uid.text=userList.get(position).id.toString()
         holder.uemail.text=userList.get(position).email
         holder.gender.text=userList.get(position).gender
         holder.status.text=userList.get(position).status
         holder.create.text=userList.get(position).createdAt
         holder.update.text=userList.get(position).updatedAt
        if (userList[position].gender=="Male"){
                holder.image.setImageResource(R.drawable.male_icon)
         }
        else{
             holder.image.setImageResource(R.drawable.female_icon)
         }
    }
    fun setusers(userList: List<Data>){
        this.userList = userList;
        notifyDataSetChanged()
    }
}
