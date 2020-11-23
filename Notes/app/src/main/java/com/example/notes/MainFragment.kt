package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.database.NotesViewModel
import com.example.notes.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding:FragmentMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var mnotesModel: NotesViewModel
    lateinit var madapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentMainBinding.inflate(inflater,container,false)
        val view=binding.root

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        binding.contents.layoutManager=layoutManager
        madapter= MyAdapter()
        binding.contents.adapter=madapter

        mnotesModel=ViewModelProvider(this).get(NotesViewModel::class.java)
        mnotesModel.readAll.observe(viewLifecycleOwner,{
            notes->madapter.setNotes(notes)
        })

        binding.add.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.main_add)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}