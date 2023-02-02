package com.gorbunova.roomfirebase.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.gorbunova.roomfirebase.model.Note

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM notes_table")// ПОЛУЧЕНИЕ ВСЕХ ЗАПИСЕЙ
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
     fun addNote(note: Note)

    @Update
     fun updateNote(note: Note)

    @Delete
     fun deleteNote(note: Note)
}