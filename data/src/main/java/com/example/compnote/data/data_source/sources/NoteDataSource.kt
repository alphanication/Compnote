package com.example.compnote.data.data_source.sources

import com.example.compnote.data.data_source.locale.models.NoteEntity
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {

    suspend fun readAll(): Flow<Resource<List<Note>>>

    suspend fun add(note: NoteEntity): Flow<Resource<Boolean>>

    suspend fun update(note: NoteEntity): Flow<Resource<Boolean>>

    suspend fun deleteNoteById(id: Int): Flow<Resource<Boolean>>

    suspend fun getNoteById(id: Int): Flow<Resource<Note>>

    suspend fun searchByTitle(title: String): Flow<Resource<List<Note>>>
}