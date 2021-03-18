package com.example.navigation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.databinding.UserListBinding
import com.example.navigation.model.ApiInterface
import com.example.navigation.model.Data
import com.example.navigation.model.RetroFitInstance
import com.example.navigation.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAdapter: RecyclerView.Adapter<MyAdapter.UserHolder>() {
    private var userList:List<Data> = listOf()
    private lateinit var binding: UserListBinding

    class UserHolder(private val binding:UserListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:Data,){
            binding.card=item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        binding=UserListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(userList[position])
        if (userList[position].gender=="Male"){
            binding.icon.setImageResource(R.drawable.male_icon)
        }
        else{
            binding.icon.setImageResource(R.drawable.female_icon)
        }
        binding.userCard.setOnClickListener {
            val cname:String=userList[position].name
            val cemail:String=userList[position].email
            val cgender:String=userList[position].gender
            val cstatus:String=userList[position].status
            val uid =userList[position].id
            val action=MainFragmentDirections.Cardtodetail(cname,cemail,cgender,cstatus,uid.toInt())
            binding.root.findNavController().navigate(action)
        }

        try {
            binding.delete.setOnClickListener {
                Log.d("Button","Button clicked")
                val service: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
                val call: Call<Unit> = service.deleteuser(userList[position].id.toInt())
                call.enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if(response.isSuccessful){
                            Log.d("Delete","$response")
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
                                    Log.d("Application", "$t")
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
           Log.d("Delete","$e")
       }
    }
    fun setusers(userList: List<Data>){
        this.userList = userList
        notifyDataSetChanged()
    }

}
