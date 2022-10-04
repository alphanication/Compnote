package com.example.compnote.data.data_source.locale.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.compnote.data.data_source.util.ConstantsStorage
import com.example.compnote.data.data_source.util.ConstantsStorage.KeysRoomNotes.NOTES_TABLE

@Entity(tableName = NOTES_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ConstantsStorage.KeysRoomNotes.ID_FIELD)
    val id: Int = 0,
    @ColumnInfo(name = ConstantsStorage.KeysRoomNotes.TITLE_FIELD)
    val title: String,
    @ColumnInfo(name = ConstantsStorage.KeysRoomNotes.SUBTITLE_FIELD)
    val subtitle: String
)