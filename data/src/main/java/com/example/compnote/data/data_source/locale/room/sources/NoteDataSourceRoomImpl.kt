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

    override suspend fun readAll(): Flow<Resource<List<Note>>> = flow {
        try {
            noteRoomDao.getAllNotes().collect { listNoteEntity ->
                emit(
                    Resource.Success(
                        NoteListMapper().mapFromEntity(listNoteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            emit(Resource.Fail(e))
        }
    }

    override suspend fun add(note: NoteEntity): Flow<Resource<Boolean>> = flow {
        try {
            noteRoomDao.addNote(note)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Fail(e))
        }
    }

    override suspend fun update(note: NoteEntity): Flow<Resource<Boolean>> = flow {
        try {
            noteRoomDao.updateNote(note)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Fail(e))
        }
    }

    override suspend fun deleteNoteById(id: Int): Flow<Resource<Boolean>> = flow {
        try {
            noteRoomDao.deleteNoteById(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Fail(e))
        }
    }

    override suspend fun getNoteById(id: Int): Flow<Resource<Note>> = flow {
        try {
            noteRoomDao.getNoteById(id).collect { noteEntity ->
                emit(
                    Resource.Success(
                        NoteMapper().mapFromEntity(noteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            emit(Resource.Fail(e))
        }
    }

    override suspend fun searchByTitle(title: String): Flow<Resource<List<Note>>> = flow {
        try {
            noteRoomDao.searchByTitle(title).collect { listNoteEntity ->
                emit(
                    Resource.Success(
                        NoteListMapper().mapFromEntity(listNoteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            emit(Resource.Fail(e))
        }
    }
}