package com.example.compnote.data.storage.room

import com.example.compnote.data.mappers.NoteListMapper
import com.example.compnote.data.mappers.NoteMapper
import com.example.compnote.data.storage.NoteStorage
import com.example.compnote.data.storage.models.NoteEntity
import com.example.compnote.data.storage.room.roomdb.NoteRoomDao
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteStorageRoomImpl @Inject constructor(
    private val noteRoomDao: NoteRoomDao
) : NoteStorage {

    override suspend fun readAll(): Flow<Response<List<Note>>> = flow {
        emit(Response.Loading())

        try {
            noteRoomDao.getAllNotes().collect { listNoteEntity ->
                emit(
                    Response.Success(
                        data = NoteListMapper().mapFromEntity(type = listNoteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            emit(Response.Fail(e = e))
        }
    }

    override suspend fun add(note: NoteEntity): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())

        try {
            noteRoomDao.addNote(note = note)
            emit(Response.Success(data = true))
        } catch (e: Exception) {
            emit(Response.Fail(e = e))
        }
    }

    override suspend fun update(note: NoteEntity): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())

        try {
            noteRoomDao.updateNote(note = note)
            emit(Response.Success(data = true))
        } catch (e: Exception) {
            emit(Response.Fail(e = e))
        }
    }

    override suspend fun deleteNoteById(id: Int): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())

        try {
            noteRoomDao.deleteNoteById(id = id)
            emit(Response.Success(data = true))
        } catch (e: Exception) {
            emit(Response.Fail(e = e))
        }
    }

    override suspend fun getNoteById(id: Int): Flow<Response<Note>> = flow {
        emit(Response.Loading())

        try {
            noteRoomDao.getNoteById(id = id).collect { noteEntity ->
                emit(
                    Response.Success(
                        data = NoteMapper().mapFromEntity(type = noteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            emit(Response.Fail(e = e))
        }
    }

    override suspend fun searchByTitle(title: String): Flow<Response<List<Note>>> = flow {
        emit(Response.Loading())

        try {
            noteRoomDao.searchByTitle(title = title).collect { listNoteEntity ->
                emit(
                    Response.Success(
                        data = NoteListMapper().mapFromEntity(type = listNoteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            emit(Response.Fail(e = e))
        }
    }
}