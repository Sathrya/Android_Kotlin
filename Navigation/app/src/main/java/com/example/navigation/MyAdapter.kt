package com.example.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.model.ApiInterface
import com.example.navigation.model.Data
import com.example.navigation.model.RetroFitInstance
import com.example.navigation.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyAdapter(): RecyclerView.Adapter<MyAdapter.UserHolder>() {
    var userList:List<Data> = listOf()
    lateinit var mcontext: Context
    lateinit var view:View

    class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var uname=itemView.findViewById<TextView>(R.id.name)
        var uid=itemView.findViewById<TextView>(R.id.uid)
        var uemail=itemView.findViewById<TextView>(R.id.email)
        var gender=itemView.findViewById<TextView>(R.id.gender)
        var status=itemView.findViewById<TextView>(R.id.status)
        var create=itemView.findViewById<TextView>(R.id.createdAt)
        var update=itemView.findViewById<TextView>(R.id.updatedAt)
        val image=itemView.findViewById<ImageView>(R.id.icon)
        var card=itemView.findViewById<CardView>(R.id.user_card)
        var delete=itemView.findViewById<ImageButton>(R.id.delete)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        mcontext=parent.context
        view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
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
        holder.card.setOnClickListener {

            val cname:String=userList[position].name
            val cemail:String=userList[position].email
            val cgender:String=userList[position].gender
            val cstatus:String=userList[position].status
            val uid: Int =userList[position].id
            val action=MainFragmentDirections.Cardtodetail(cname,cemail,cgender,cstatus,uid)
            Navigation.findNavController(view).navigate(action)
        }
        try {
            //Invoking DELETE request
            holder.delete.setOnClickListener {
                // userList.(position)
                Log.d("Button","Button clicked")
                val service: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
                val call: Call<Unit> = service.deleteuser(userList[position].id)
                call.enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if(response.isSuccessful){
                            Toast.makeText(mcontext,"Status:${response.code()},User Deleted Successfully",Toast.LENGTH_SHORT).show()
                            Log.d("Delete","${response}")
                            val server: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
                            val getcall: Call<Users> = service.getUserData()
                            getcall.enqueue(object : Callback<Users> {
                                override fun onResponse(call: Call<Users>, response: Response<Users>) {
                                    Log.d("Repo", "${ response.code()}")
                                    if(response.isSuccessful)
                                    {
                                        setusers(response.body()!!.data)
                                    }
                                    else{
                                        Log.d("Application", "404 Not found")
                                    }

                                }
                                override fun onFailure(call: Call<Users>, t: Throwable) {
                                    Log.d("Application", "${t}")
                                }
                            })
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Log.d("Remove","Network")
                    }

                })
                notifyDataSetChanged()
            }
        }
       catch (e:NullPointerException){
           Log.d("Delete","${e}")
       }

    }
    fun setusers(userList: List<Data>){
        this.userList = userList
        notifyDataSetChanged()
    }
}
