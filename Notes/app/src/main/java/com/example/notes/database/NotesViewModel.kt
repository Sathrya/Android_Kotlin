package com.example.notes.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.notes.R
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.notes.model.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {
        val readAll:LiveData<List<Model>>
        private val notesrepo:NotesRepository

    init {
            val notesDao=NotesDatabase.getDatabase(application).notesDao()
            notesrepo= NotesRepository(notesDao)
            readAll=notesrepo.readAllData
    }


    fun addNote(note:Model){
        viewModelScope.launch(Dispatchers.IO){
            notesrepo.addnote(note)
        }
    }

    fun viewNote(note: Model){
        viewModelScope.launch(Dispatchers.IO){
            notesrepo.updateNOte(note)
        }
    }

    fun deleteNote(note: Model){
        viewModelScope.launch(Dispatchers.IO){
            notesrepo.deleteNote(note)
        }
    }
}