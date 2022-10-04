package com.example.compnote.di

import android.content.Context
import com.example.compnote.data.data_source.room.roomdb.AppRoomDatabase
import com.example.compnote.data.data_source.room.roomdb.NoteRoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideAppRoomDatabase(
        @ApplicationContext appContext: Context
    ): AppRoomDatabase = AppRoomDatabase.getAppDB(appContext)

    @Singleton
    @Provides
    fun provideDatabaseNoteRoomDao(roomDatabase: AppRoomDatabase): NoteRoomDao =
        roomDatabase.getNoteDao()
}