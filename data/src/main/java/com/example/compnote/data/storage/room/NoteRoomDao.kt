package com.example.compnote.data.storage.room

import androidx.room.*
import com.example.compnote.data.storage.models.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteRoomDao {
    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes_table WHERE id = :id")
    fun getNoteById(id: Int) : Flow<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}