package com.example.notes

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notes.database.NotesViewModel
import com.example.notes.databinding.FragmentAddBinding
import com.example.notes.model.model

class AddFragment : Fragment() {
    lateinit var mNotesViewModel: NotesViewModel
    private var _binding:FragmentAddBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAddBinding.inflate(inflater,container,false)
        val view=binding.root
        mNotesViewModel=ViewModelProvider(this).get(NotesViewModel::class.java)
        binding.submit.setOnClickListener {
            val title=binding.title.text.toString()
            val note=binding.note.text.toString()
            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(note)){
                Toast.makeText(requireContext(),"None of the fields can be empty",Toast.LENGTH_SHORT).show()
            }
            else{
                val notes=model(0,title,note)
                mNotesViewModel.addNote(notes)
                Toast.makeText(requireContext(),"Note added",Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.add_main)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}