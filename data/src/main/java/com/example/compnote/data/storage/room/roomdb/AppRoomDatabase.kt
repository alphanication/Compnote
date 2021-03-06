package com.example.compnote.data.storage.room.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.compnote.data.storage.models.NoteEntity
import com.example.compnote.data.storage.util.ConstantsStorage

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteRoomDao

    companion object {
        private var dbInstance: AppRoomDatabase? = null

        fun getAppDB(context: Context): AppRoomDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    ConstantsStorage.Keys.NOTES_DATABASE
                ).build()
            }

            return dbInstance!!
        }
    }
}
