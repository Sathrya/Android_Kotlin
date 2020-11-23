package com.example.notes.database

import androidx.lifecycle.LiveData
import com.example.notes.model.model

class NotesRepository(val notesDao: NotesDao) {
    val readAllData:LiveData<List<model>> = notesDao.listNotes()

    suspend fun addnote(note:model){
       notesDao.addnote(note)
}
    suspend fun updateNOte(note: model){
        notesDao.updateNote(note)
    }

    suspend fun deleteNote(note: model){
        notesDao.deleteNote(note)
    }

}