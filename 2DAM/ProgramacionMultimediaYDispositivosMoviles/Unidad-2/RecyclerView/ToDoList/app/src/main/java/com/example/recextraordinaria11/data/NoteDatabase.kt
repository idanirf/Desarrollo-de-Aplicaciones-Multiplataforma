package com.example.recextraordinaria11.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recextraordinaria11.model.Note

@Database(entities = [Note::class], version = 2)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun notesDao(): NoteDAO
}