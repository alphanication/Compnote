package com.example.compnote.data.storage.room

import com.example.compnote.data.mappers.NoteListMapper
import com.example.compnote.data.mappers.NoteMapper
import com.example.compnote.data.storage.NoteStorage
import com.example.compnote.data.storage.models.NoteEntity
import com.example.compnote.data.storage.room.roomdb.NoteRoomDao
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Response
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NoteStorageRoomImpl @Inject constructor(
    private val noteRoomDao: NoteRoomDao
) : NoteStorage {

    override suspend fun readAll(): Flow<Response<List<Note>>> = callbackFlow {
        trySend(Response.Loading())

        try {
            noteRoomDao.getAllNotes().collect { listNoteEntity ->
                trySend(
                    Response.Success(
                        data = NoteListMapper().mapFromEntity(type = listNoteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            trySend(Response.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun add(note: NoteEntity): Flow<Response<Boolean>> = callbackFlow {
        trySend(Response.Loading())

        try {
            noteRoomDao.addNote(note = note)
            trySend(Response.Success(data = true))
        } catch (e: Exception) {
            trySend(Response.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun update(note: NoteEntity): Flow<Response<Boolean>> = callbackFlow {
        trySend(Response.Loading())

        try {
            noteRoomDao.updateNote(note = note)
            trySend(Response.Success(data = true))
        } catch (e: Exception) {
            trySend(Response.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun deleteNoteById(id: Int): Flow<Response<Boolean>> = callbackFlow {
        trySend(Response.Loading())

        try {
            noteRoomDao.deleteNoteById(id = id)
            trySend(Response.Success(data = true))
        } catch (e: Exception) {
            trySend(Response.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun getNoteById(id: Int): Flow<Response<Note>> = callbackFlow {
        trySend(Response.Loading())

        try {
            noteRoomDao.getNoteById(id = id).collect { noteEntity ->
                trySend(
                    Response.Success(
                        data = NoteMapper().mapFromEntity(type = noteEntity)
                    )
                )
            }
        } catch (e: Exception) {
            trySend(Response.Fail(e = e))
        }

        awaitClose { this.cancel() }
    }
}