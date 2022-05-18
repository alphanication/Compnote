package com.example.compnote.data.storage

import com.example.compnote.data.storage.models.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteStorage {
    suspend fun readAll(): Flow<List<NoteEntity>>

    suspend fun add(note: NoteEntity): Flow<Boolean>

    suspend fun update(note: NoteEntity): Flow<Boolean>

    suspend fun delete(note: NoteEntity): Flow<Boolean>
}