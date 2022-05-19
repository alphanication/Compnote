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
    fun addNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)

    @Query("DELETE FROM notes_table WHERE id = :id")
    fun deleteNoteById(id: Int)
}