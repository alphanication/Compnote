package com.example.compnote.data.data_source.locale.room.sources

import com.example.compnote.data.mappers.NoteListMapper
import com.example.compnote.data.mappers.NoteMapper
import com.example.compnote.data.data_source.sources.NoteDataSource
import com.example.compnote.data.data_source.locale.models.NoteEntity
import com.example.compnote.data.data_source.locale.room.dao.NoteRoomDao
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Resource
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteDataSourceRoomImpl @Inject constructor(
    private val noteRoomDao: NoteRoomDao
) : NoteDataSource {

    override suspend fun readAll(): Flow<Resource<List<Note>>> = callbackFlow {
        trySend(Resource.Loading())

        try {
            noteRoomDao.getAllNotes().collect { listNoteEntity ->
                trySend(
                    Resource.Success(
                        data = NoteListMapper().mapFromEntity(type = listNoteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            trySend(Resource.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun add(note: NoteEntity): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())

        try {
            noteRoomDao.addNote(note = note)
            trySend(Resource.Success(data = true))
        } catch (e: Exception) {
            trySend(Resource.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun update(note: NoteEntity): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())

        try {
            noteRoomDao.updateNote(note = note)
            trySend(Resource.Success(data = true))
        } catch (e: Exception) {
            trySend(Resource.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun deleteNoteById(id: Int): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())

        try {
            noteRoomDao.deleteNoteById(id = id)
            trySend(Resource.Success(data = true))
        } catch (e: Exception) {
            trySend(Resource.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun getNoteById(id: Int): Flow<Resource<Note>> = callbackFlow {
        trySend(Resource.Loading())

        try {
            noteRoomDao.getNoteById(id = id).collect { noteEntity ->
                trySend(
                    Resource.Success(
                        data = NoteMapper().mapFromEntity(type = noteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            trySend(Resource.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun searchByTitle(title: String): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())

        try {
            noteRoomDao.searchByTitle(title = title).collect { listNoteEntity ->
                emit(
                    Resource.Success(
                        data = NoteListMapper().mapFromEntity(type = listNoteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            emit(Resource.Fail(e = e))
        }
    }
}