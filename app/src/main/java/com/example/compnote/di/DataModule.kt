package com.example.compnote.di

import com.example.compnote.data.repository.NoteRepositoryImpl
import com.example.compnote.data.data_source.sources.NoteDataSource
import com.example.compnote.data.data_source.locale.room.sources.NoteDataSourceRoomImpl
import com.example.compnote.data.data_source.locale.room.dao.NoteRoomDao
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
    fun provideNoteDataSource(noteRoomDao: NoteRoomDao): NoteDataSource =
        NoteDataSourceRoomImpl(noteRoomDao)

    @Provides
    @Singleton
    fun provideNoteRepository(noteStorage: NoteDataSource): NoteRepository =
        NoteRepositoryImpl(noteStorage)
}