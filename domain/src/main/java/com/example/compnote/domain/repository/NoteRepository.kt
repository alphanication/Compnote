package com.example.compnote.domain.repository

import com.example.compnote.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun readAll(): Flow<List<Note>>

    suspend fun add(note: Note): Flow<Boolean>

    suspend fun update(note: Note): Flow<Boolean>

    suspend fun delete(note: Note): Flow<Boolean>
}