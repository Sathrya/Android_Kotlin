package com.example.notes.database

import androidx.lifecycle.LiveData
import com.example.notes.model.Model

class NotesRepository(val notesDao: NotesDao) {
    val readAllData:LiveData<List<Model>> = notesDao.listNotes()

    suspend fun addnote(note:Model){
       notesDao.addnote(note)
}
    suspend fun updateNOte(note: Model){
        notesDao.updateNote(note)
    }

    suspend fun deleteNote(note: Model){
        notesDao.deleteNote(note)
    }

}