package com.example.cricbuzztask

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzztask.adapters.MenuAdapter
import com.example.cricbuzztask.adapters.MyAdapter
import com.example.cricbuzztask.databinding.ActivityMainBinding
import com.example.cricbuzztask.db.MyDatabase
import com.example.cricbuzztask.db.MyRepo
import com.example.cricbuzztask.model.Menu.MenuItems
import com.example.cricbuzztask.model.Restaurant.ModelItem
import com.example.cricbuzztask.model.Restaurant.Review
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

    private lateinit var myViewModel: MyViewModel
    private lateinit var myAdapter: MyAdapter
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelFactory: ViewModelFatctory
    private lateinit var repo: MyRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        myAdapter= MyAdapter()
        menuAdapter= MenuAdapter()
        repo= MyRepo(MyDatabase.getDatabase(this))
        viewModelFactory=ViewModelFatctory(repo)
        myViewModel= ViewModelProvider(this,viewModelFactory)[MyViewModel::class.java]

        loadIntialData()

        binding.container.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.container.adapter=myAdapter

        binding.menuContainer.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.menuContainer.adapter=menuAdapter

        myViewModel.readAll.observe(this, Observer {
            restList->myAdapter.setData(restList)
            Log.d("Main Activity",restList.toString())
        })

        myViewModel.readAllMenu.observe(this, Observer {
            menuList->menuAdapter.setData(menuList)
            Log.d("Main Activity Menu",menuList.toString())
        })
    }

    private fun loadIntialData() {

        try{
            val reviews =  ArrayList<Review>()
            val items = loadJSONArray(this,0)
            for (i in 0 until items.length()){
                val item= items.getJSONObject(i)
                val address=item.getString("address")
                val name=item.getString("name")
                val cuisine=item.getString("cuisine_type")
                val id=item.getInt("id")
                val neighborhood=item.getString("neighborhood")

                val modelItem= ModelItem(address,cuisine,id,name, neighborhood)

                myViewModel.saveData(modelItem)

            }

        }
        catch (ex: JSONException){

        }

        try{
            val items = loadJSONArray(this,1)
            Log.d("Main Items",items.toString())
            for (i in 0 until items.length()){
                val item= items.getJSONObject(i)
                val name=item.getString("name")
                val description=item.getString("description")
                val id=item.getString("id")
                val price=item.getString("price")

                val menuItem= MenuItems(description,id,name,price)

                myViewModel.saveMenuData(menuItem)

            }

        }
        catch (ex: JSONException){

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        val search=menu?.findItem(R.id.search)
        val searchView=search?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

        return true
    }

    private fun loadJSONArray(context: Context,type:Int): JSONArray {

        if (type==0){
            val input =context.resources.openRawResource(R.raw.data)
            BufferedReader(input.reader()).use {
                return JSONArray(it.readText())
            }
        }else{
            val input =context.resources.openRawResource(R.raw.menu)
            BufferedReader(input.reader()).use {
                return JSONArray(it.readText())
            }
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText!=null){
            searchQuery(newText)
        }
        return true
    }

    private fun searchQuery(query:String){

        val searchquery="%$query%"
        myViewModel.searchData(searchquery).observe(this, Observer {
            myAdapter.setData(it)
        })
        myViewModel.searchMenuData(searchquery).observe(this, Observer {
            menuAdapter.setData(it)
        })

    }


}