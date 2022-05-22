package com.example.compnote.domain.repository

import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Response
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun readAll(): Flow<Response<List<Note>>>

    suspend fun add(note: Note): Flow<Response<Boolean>>

    suspend fun update(note: Note): Flow<Response<Boolean>>

    suspend fun deleteNoteById(id: Int): Flow<Response<Boolean>>

    suspend fun getNoteById(id: Int): Flow<Response<Note>>

    suspend fun searchByTitle(title: String) : Flow<Response<List<Note>>>
}