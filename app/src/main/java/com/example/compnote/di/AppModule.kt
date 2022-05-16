package com.example.compnote.di

import android.content.Context
import androidx.room.Room
import com.example.compnote.database.room.AppRoomDatabase
import com.example.compnote.database.room.repository.DatabaseRepositoryRoomImpl
import com.example.compnote.database.room.dao.NoteRoomDao
import com.example.compnote.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppRoomDatabase(
        @ApplicationContext appContext: Context
    ): AppRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            AppRoomDatabase::class.java,
            Constants.Keys.NOTES_DATABASE
        ).build()
    }

    @Singleton
    @Provides
    fun provideDatabaseNoteRoomDao(roomDatabase: AppRoomDatabase) : NoteRoomDao {
        return roomDatabase.getRoomDao()
    }

    @Singleton
    @Provides
    fun providesDatabaseRepositoryRoomImpl(noteRoomDao: NoteRoomDao) : DatabaseRepositoryRoomImpl {
        return DatabaseRepositoryRoomImpl(noteRoomDao = noteRoomDao)
    }
}
