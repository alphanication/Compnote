package com.example.compnote.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val subtitle: String
)