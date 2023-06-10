package com.example.recextraordinaria11.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.recextraordinaria11.model.Note

@Dao
interface NoteDAO {
    @Insert
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note_table")
    suspend fun getAllNote(): MutableList<Note>

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}