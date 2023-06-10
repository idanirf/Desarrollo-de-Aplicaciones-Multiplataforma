package com.example.recextraordinaria11.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    var subTitle: String,
    var descripcion: String,
    var category: String,
    var tick: Boolean = false,
    var photoUrl: String
): Parcelable
