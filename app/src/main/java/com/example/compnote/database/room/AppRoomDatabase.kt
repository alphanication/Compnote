package com.example.compnote.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.compnote.database.room.dao.NoteRoomDao
import com.example.compnote.models.Note

@Database(entities = [Note::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getRoomDao(): NoteRoomDao

    companion object {

        @Volatile
        private var INSTANSE: AppRoomDatabase? = null

        fun getInstance(context: Context) : AppRoomDatabase {
            return if(INSTANSE == null) {
                INSTANSE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANSE as AppRoomDatabase
            } else INSTANSE as AppRoomDatabase
        }
    }
}