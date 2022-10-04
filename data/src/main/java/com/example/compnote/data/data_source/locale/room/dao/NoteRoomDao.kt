package com.example.compnote.data.data_source.locale.room.dao

import androidx.room.*
import com.example.compnote.data.data_source.locale.models.NoteEntity
import com.example.compnote.data.data_source.util.ConstantsStorage
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM ${ConstantsStorage.KeysRoomNotes.NOTES_TABLE}")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query(
        "SELECT * FROM ${ConstantsStorage.KeysRoomNotes.NOTES_TABLE} WHERE " +
                "${ConstantsStorage.KeysRoomNotes.ID_FIELD} = :id"
    )
    fun getNoteById(id: Int): Flow<NoteEntity>

    @Query(
        "SELECT * FROM ${ConstantsStorage.KeysRoomNotes.NOTES_TABLE} WHERE " +
                "${ConstantsStorage.KeysRoomNotes.TITLE_FIELD} LIKE '%' || :title || '%'"
    )
    fun searchByTitle(title: String): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)

    @Query(
        "DELETE FROM ${ConstantsStorage.KeysRoomNotes.NOTES_TABLE} WHERE " +
                "${ConstantsStorage.KeysRoomNotes.ID_FIELD} = :id"
    )
    fun deleteNoteById(id: Int)
}