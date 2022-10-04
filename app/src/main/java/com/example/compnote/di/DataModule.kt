package com.example.compnote.di

import com.example.compnote.data.repository.NoteRepositoryImpl
import com.example.compnote.data.data_source.NoteDataSource
import com.example.compnote.data.data_source.room.NoteDataSourceRoomImpl
import com.example.compnote.data.data_source.room.roomdb.NoteRoomDao
import com.example.compnote.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideNoteStorage(noteRoomDao: NoteRoomDao): NoteDataSource {
        return NoteDataSourceRoomImpl(noteRoomDao = noteRoomDao)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteStorage: NoteDataSource): NoteRepository {
        return NoteRepositoryImpl(noteStorage = noteStorage)
    }
}