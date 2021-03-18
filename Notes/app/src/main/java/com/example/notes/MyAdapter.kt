package com.example.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.CardViewBinding
import com.example.notes.model.Model


class MyAdapter : RecyclerView.Adapter<MyAdapter.NoteHolder>() {
    lateinit var binding: CardViewBinding
    private var notesList:List<Model> = listOf()
    class NoteHolder(private var binding: CardViewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Model){
            binding.data=item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        binding=CardViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(notesList[position])
        binding.card.setOnClickListener {
            val title=notesList[position].title
            val note=notesList[position].note
            val id=notesList[position].id
            val navigate=MainFragmentDirections.mainNote(title,note, id)
            binding.root.findNavController().navigate(navigate)
    }
}

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setNotes(notes:List<Model>){
        this.notesList=notes
        notifyDataSetChanged()
    }
}