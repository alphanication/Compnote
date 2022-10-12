package com.example.compnote.data.repository

import com.example.compnote.data.mappers.NoteMapper
import com.example.compnote.data.data_source.sources.NoteDataSource
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Resource
import com.example.compnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteStorage: NoteDataSource
) : NoteRepository {

    override suspend fun readAll(): Flow<Resource<List<Note>>> =
        noteStorage.readAll()

    override suspend fun add(note: Note): Flow<Resource<Boolean>> =
        noteStorage.add(
            NoteMapper().mapToEntity(note)
        )

    override suspend fun update(note: Note): Flow<Resource<Boolean>> =
        noteStorage.update(
            NoteMapper().mapToEntity(note)
        )

    override suspend fun deleteNoteById(id: Int): Flow<Resource<Boolean>> =
        noteStorage.deleteNoteById(id)

    override suspend fun getNoteById(id: Int): Flow<Resource<Note>> =
        noteStorage.getNoteById(id)

    override suspend fun searchByTitle(title: String): Flow<Resource<List<Note>>> =
        noteStorage.searchByTitle(title)
}