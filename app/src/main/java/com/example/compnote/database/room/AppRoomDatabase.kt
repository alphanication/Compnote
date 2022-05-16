package com.example.compnote.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.compnote.database.room.dao.NoteRoomDao
import com.example.compnote.models.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getRoomDao(): NoteRoomDao
}