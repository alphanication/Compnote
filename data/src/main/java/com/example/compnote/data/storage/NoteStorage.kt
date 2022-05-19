package com.example.compnote.data.storage

import com.example.compnote.data.storage.models.NoteEntity
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Response
import kotlinx.coroutines.flow.Flow

interface NoteStorage {
    suspend fun readAll(): Flow<Response<List<Note>>>

    suspend fun add(note: NoteEntity): Flow<Response<Boolean>>

    suspend fun update(note: NoteEntity): Flow<Response<Boolean>>

    suspend fun deleteNoteById(id: Int): Flow<Response<Boolean>>

    suspend fun getNoteById(id: Int): Flow<Response<Note>>
}