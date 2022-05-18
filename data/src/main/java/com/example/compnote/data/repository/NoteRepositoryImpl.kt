package com.example.compnote.data.repository

import com.example.compnote.data.mappers.NoteArrayListMapper
import com.example.compnote.data.mappers.NoteMapper
import com.example.compnote.data.storage.NoteStorage
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val noteStorage: NoteStorage
) : NoteRepository {
    override suspend fun readAll(): Flow<List<Note>> {
        return noteStorage.readAll().map {
            NoteArrayListMapper().mapFromEntity(it)
        }
    }

    override suspend fun add(note: Note): Flow<Boolean> {
        return noteStorage.add(
            note = NoteMapper().mapToEntity(note)
        )
    }

    override suspend fun update(note: Note): Flow<Boolean> {
        return noteStorage.update(
            note = NoteMapper().mapToEntity(note)
        )
    }

    override suspend fun delete(note: Note): Flow<Boolean> {
        return noteStorage.delete(
            note = NoteMapper().mapToEntity(note)
        )
    }
}