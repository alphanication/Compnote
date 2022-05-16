package com.example.compnote.database

import androidx.lifecycle.LiveData
import com.example.compnote.models.Note

interface DatabaseRepository {
    val readAll: LiveData<List<Note>>

    suspend fun add(note: Note, onSuccess: () -> Unit)

    suspend fun update(note: Note, onSuccess: () -> Unit)

    suspend fun delete(note: Note, onSuccess: () -> Unit)
}