package com.example.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.model.Data


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
        var delete=itemView.findViewWithTag<ImageButton>(R.id.delete)

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
            val uid: String =userList[position].id.toString()
            val action=MainFragmentDirections.Cardtodetail(cname,cemail,cgender,cstatus)
            Navigation.findNavController(view).navigate(action)
           /* val bundle = Bundle()
            bundle.putInt("id",uid)
            bundle.putString("name",cname)
            bundle.putString("email",cemail)
            bundle.putString("gender",cgender)
            bundle.putString("status",cstatus)
            val intent = Intent(mcontext, CardActivity::class.java)
            intent.putExtras(bundle)
            startActivity(mcontext, intent, null)*/
        }
        /* holder.delete.setOnClickListener {
            // userList.(position)
             val service: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
             val call: Call<Unit> = service.deleteuser(position)
             call.enqueue(object :Callback<Unit>{
                 override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if(response.isSuccessful){
                        Toast.makeText(mcontext,"Status:${response.code()},User Deleted Successfully",Toast.LENGTH_SHORT).show()
                    }
                 }

                 override fun onFailure(call: Call<Unit>, t: Throwable) {
                     Log.d("Remove","Network")
                 }

             })
             notifyDataSetChanged()
         }*/
    }
    fun setusers(userList: List<Data>){
        this.userList = userList
        notifyDataSetChanged()
    }
}