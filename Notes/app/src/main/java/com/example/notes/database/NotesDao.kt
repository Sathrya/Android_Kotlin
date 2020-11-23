package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.model.model

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addnote(note:model)

    @Delete
    suspend fun deleteNote(note: model)

    @Update
    suspend fun updateNote(note: model)

    @Query("select * from notes_table")
    fun listNotes():LiveData<List<model>>
}