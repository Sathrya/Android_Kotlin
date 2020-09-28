package com.example.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.model.ApiInterface
import com.example.navigation.model.RetroFitInstance
import com.example.navigation.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {
   private lateinit var rcview:RecyclerView
    lateinit var madapter: MyAdapter
    private lateinit var mcontext:Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mcontext= context!!
        val view= inflater.inflate(R.layout.fragment_main, container, false)
       // view.text.setOnClickListener { Navigation.findNavController(view).navigate(R.id.Card_to_detail) }
        rcview=view.findViewById(R.id.contents)
        madapter = MyAdapter()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(mcontext,
            RecyclerView.VERTICAL,false)
        rcview.layoutManager = layoutManager
        rcview.adapter = madapter

        val service: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        val call: Call<Users> = service.getUserData()

        call.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                Log.d("Repo", "${ response.code()}")
                if(response.isSuccessful)
                {
                    Log.d("Repo", "${response.body()?.data}")
                    madapter.setusers(response.body()!!.data)
                }
                else{
                    Log.d("Application", "404 Not found")
                }

            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("Application", "$t")
            }
        })
        return view
    }


}