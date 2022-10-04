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

    override suspend fun readAll(): Flow<Response<List<Note>>> =
        noteStorage.readAll()

    override suspend fun add(note: Note): Flow<Response<Boolean>> =
        noteStorage.add(
            NoteMapper().mapToEntity(note)
        )

    override suspend fun update(note: Note): Flow<Response<Boolean>> =
        noteStorage.update(
            NoteMapper().mapToEntity(note)
        )

    override suspend fun deleteNoteById(id: Int): Flow<Response<Boolean>> =
        noteStorage.deleteNoteById(id)

    override suspend fun getNoteById(id: Int): Flow<Response<Note>> =
        noteStorage.getNoteById(id)

    override suspend fun searchByTitle(title: String): Flow<Response<List<Note>>> =
        noteStorage.searchByTitle(title)
}