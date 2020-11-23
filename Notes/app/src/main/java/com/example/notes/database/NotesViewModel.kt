package com.example.notes.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.model.model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {
        val readAll:LiveData<List<model>>
        private val notesrepo:NotesRepository

    init {
            val notesDao=NotesDatabase.getDatabase(application).notesDao()
            notesrepo= NotesRepository(notesDao)
            readAll=notesrepo.readAllData
    }

    fun addNote(note:model){
        viewModelScope.launch(Dispatchers.IO){
            notesrepo.addnote(note)
        }
    }

    fun viewNote(note: model){
        viewModelScope.launch(Dispatchers.IO){
            notesrepo.updateNOte(note)
        }
    }

    fun deleteNote(note: model){
        viewModelScope.launch(Dispatchers.IO){
            notesrepo.deleteNote(note)
        }
    }


}