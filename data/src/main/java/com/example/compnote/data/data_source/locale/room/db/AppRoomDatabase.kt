package com.example.compnote.data.data_source.locale.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.compnote.data.data_source.locale.models.NoteEntity
import com.example.compnote.data.data_source.locale.room.dao.NoteRoomDao

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteRoomDao
}
