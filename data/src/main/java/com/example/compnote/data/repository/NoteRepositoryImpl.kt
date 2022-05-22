package com.example.compnote.data.repository

import com.example.compnote.data.mappers.NoteMapper
import com.example.compnote.data.storage.NoteStorage
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Response
import com.example.compnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteStorage: NoteStorage
) : NoteRepository {
    override suspend fun readAll(): Flow<Response<List<Note>>> {
        return noteStorage.readAll()
    }

    override suspend fun add(note: Note): Flow<Response<Boolean>> {
        return noteStorage.add(
            note = NoteMapper().mapToEntity(note)
        )
    }

    override suspend fun update(note: Note): Flow<Response<Boolean>> {
        return noteStorage.update(
            note = NoteMapper().mapToEntity(note)
        )
    }

    override suspend fun deleteNoteById(id: Int): Flow<Response<Boolean>> {
        return noteStorage.deleteNoteById(id = id)
    }

    override suspend fun getNoteById(id: Int): Flow<Response<Note>> {
        return noteStorage.getNoteById(id = id)
    }

    override suspend fun searchByTitle(title: String): Flow<Response<List<Note>>> {
        return noteStorage.searchByTitle(title = title)
    }
}