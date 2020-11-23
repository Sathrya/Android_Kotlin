package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.model.model

class MyAdapter : RecyclerView.Adapter<MyAdapter.NoteHolder>() {

    private var notesList:List<model> = listOf()
    private lateinit var view: View
    class NoteHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var titleText: TextView =itemView.findViewById(R.id.title)
        var note: TextView = itemView.findViewById(R.id.notes)!!
        var noteCard: CardView =itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.titleText.text= notesList[position].title
        holder.note.text= notesList[position].note
        holder.noteCard.setOnClickListener {
            val title=notesList[position].title
            val note=notesList[position].note
            val id=notesList[position].id
            val navigate=MainFragmentDirections.mainNote(title,note, id)
            Navigation.findNavController(view).navigate(navigate)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setNotes(notes:List<model>){
        this.notesList=notes
        notifyDataSetChanged()
    }

}