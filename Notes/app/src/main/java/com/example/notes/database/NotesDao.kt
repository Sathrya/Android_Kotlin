package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.model.Model

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addnote(note: Model)

    @Delete
    suspend fun deleteNote(note: Model)

    @Update
    suspend fun updateNote(note: Model)

    @Query("select * from notes_table")
    fun listNotes():LiveData<List<Model>>
}