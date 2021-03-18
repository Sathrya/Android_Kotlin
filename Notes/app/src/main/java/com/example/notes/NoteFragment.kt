package com.example.notes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notes.database.NotesViewModel
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.model.Model

class NoteFragment : Fragment() {
    private lateinit var mNotesViewModel: NotesViewModel
    private val args: NoteFragmentArgs by navArgs()
    private var _binding: FragmentNoteBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentNoteBinding.inflate(inflater,container,false)
        val view=binding.root
        mNotesViewModel=ViewModelProvider(this).get(NotesViewModel::class.java)

        val title=args.title
        val note=args.note

        binding.titleText.text=title
        binding.noteText.setText(note,TextView.BufferType.EDITABLE)

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(item.itemId==R.id.delete){
            val deleteNote= Model(args.id,args.title,args.note)
            val builder= AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){
                    _,_->mNotesViewModel.deleteNote(deleteNote)
                Toast.makeText(requireContext(), "Note Removed ", Toast.LENGTH_LONG).show()
                view?.let { Navigation.findNavController(it).navigate(R.id.note_main) }
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${args.title}?")
            builder.setMessage("Are you sure you want to delete ${args.title}?")
            builder.create().show()
        }
        else if(item.itemId==R.id.update){
            val updatedNote=binding.noteText.text.toString()
            val update=Model(args.id,args.title,updatedNote)
            mNotesViewModel.viewNote(update)
           Toast.makeText(requireContext(), "Note Updated ", Toast.LENGTH_LONG).show()
           view?.let { Navigation.findNavController(it).navigate(R.id.note_main) }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}