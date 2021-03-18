package com.example.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.databinding.FragmentMainBinding
import com.example.navigation.model.ApiInterface
import com.example.navigation.model.RetroFitInstance
import com.example.navigation.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private lateinit var bind:FragmentMainBinding

class MainFragment : Fragment(){
    lateinit var madapter: MyAdapter
    private lateinit var mcontext:Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind=DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        val view=bind.root
        mcontext= requireContext()
        madapter = MyAdapter()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
            mcontext,
            RecyclerView.VERTICAL,
            false
        )

        bind.contents.layoutManager=layoutManager
        bind.contents.adapter=madapter
        bind.listener=ClickHandlers()


        val service: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        val call: Call<Users> = service.getUserData()

        call.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                Log.d("Repo", "${response.code()}")
                if (response.isSuccessful) {
                    Log.d("Repo", "$response")
                    madapter.setusers(response.body()!!.data)
                } else {
                    Log.d("Application", "404 Not found")
                }
            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("Application", "$t")
            }
        })
        return view
    }

    class ClickHandlers {
        fun navigate(){
            bind.root.findNavController().navigate(R.id.CardtoPost)
        }
    }
}