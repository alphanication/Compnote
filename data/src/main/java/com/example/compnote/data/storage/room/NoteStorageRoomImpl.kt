package com.example.compnote.data.storage.room

import com.example.compnote.data.storage.NoteStorage
import com.example.compnote.data.storage.models.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class NoteStorageRoomImpl(
    private val noteRoomDao: NoteRoomDao
) : NoteStorage {

    override suspend fun readAll(): Flow<List<NoteEntity>> {
        return noteRoomDao.getAllNotes()
    }

    override suspend fun add(note: NoteEntity): Flow<Boolean> = flow {
        noteRoomDao.addNote(note = note)
        emit(true)
    }.catch { emit(false) }

    override suspend fun update(note: NoteEntity): Flow<Boolean> = flow {
        noteRoomDao.updateNote(note = note)
        emit(true)
    }.catch { emit(false) }

    override suspend fun delete(note: NoteEntity): Flow<Boolean> = flow {
        noteRoomDao.deleteNote(note = note)
        emit(true)
    }.catch { emit(false) }

    override suspend fun getNoteById(id: Int): Flow<NoteEntity> = flow {
        noteRoomDao.getNoteById(id = id).collect { emit(it) }
    }
}