package com.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.database.NotesViewModel
import com.example.notes.databinding.FragmentMainBinding

lateinit var bind:FragmentMainBinding
class MainFragment : Fragment() {

    private lateinit var mnotesModel: NotesViewModel
    private lateinit var madapter: MyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        bind=DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        val view=bind.root
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)

        bind.contents.layoutManager=layoutManager
        madapter= MyAdapter()
        bind.contents.adapter=madapter
        bind.listener= ClickHandler()

        mnotesModel=ViewModelProvider(this).get( NotesViewModel::class.java )
        mnotesModel.readAll.observe(viewLifecycleOwner,{
            notes->madapter.setNotes(notes)
        })
        return view
    }

    class ClickHandler{
        fun navigate(){
            bind.root.findNavController().navigate(R.id.main_add)
        }
    }
}