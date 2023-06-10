package com.example.recextraordinaria11.adapter

import com.example.recextraordinaria11.model.Note

interface NoteOnClickListener {
    fun onClickEdit(note: Note)
    fun onClickDelete(note: Note)
}