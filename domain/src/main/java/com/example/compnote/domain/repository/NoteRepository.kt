package com.example.compnote.domain.repository

import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun readAll(): Flow<Resource<List<Note>>>

    suspend fun add(note: Note): Flow<Resource<Boolean>>

    suspend fun update(note: Note): Flow<Resource<Boolean>>

    suspend fun deleteNoteById(id: Int): Flow<Resource<Boolean>>

    suspend fun getNoteById(id: Int): Flow<Resource<Note>>

    suspend fun searchByTitle(title: String): Flow<Resource<List<Note>>>
}