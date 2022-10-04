package com.example.compnote.data.data_source.room.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.compnote.data.data_source.models.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteRoomDao
}
