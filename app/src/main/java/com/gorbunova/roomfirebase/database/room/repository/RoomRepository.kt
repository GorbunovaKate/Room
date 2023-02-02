package com.gorbunova.roomfirebase.database.room.repository

import androidx.lifecycle.LiveData
import com.gorbunova.roomfirebase.database.DatabaseRepository
import com.gorbunova.roomfirebase.database.room.dao.NoteRoomDao
import com.gorbunova.roomfirebase.model.Note

class RoomRepository(private val noteRoomDao: NoteRoomDao): DatabaseRepository {
    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
        onSuccess()
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
        onSuccess()
    }
}