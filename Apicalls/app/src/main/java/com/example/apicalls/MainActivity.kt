package com.example.apicalls

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalls.model.ApiInterface
import com.example.apicalls.model.Data
import com.example.apicalls.model.MyAdapter
import com.example.apicalls.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rcview:RecyclerView
    lateinit var madapter: MyAdapter
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var service:ApiInterface
    var page=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcview = findViewById(R.id.contents)
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        madapter = MyAdapter(mutableListOf())
        rcview.layoutManager = layoutManager
        rcview.adapter = madapter
        service= RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        hitApi(page)
        rcview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstItem = layoutManager.findFirstVisibleItemPosition()

                if (firstItem + visibleItemCount >= totalItemCount) {
                    /*rcview.removeOnScrollListener(this)*/
                    page++
                    Log.d("Recycler", page.toString())
                    hitApi(page)
                }
            }
        })
    }

    fun hitApi(page:Int){
        val call: Call<Users> = service.getUserData(page)
        call.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    madapter.appendusers(response.body()!!.data as MutableList<Data>)
                } else {
                    Log.d("Application", "404 Not found")
                }
            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("Application", "Network Failure")
            }
        })
    }
}