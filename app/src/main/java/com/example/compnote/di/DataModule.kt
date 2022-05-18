package com.example.compnote.di

import com.example.compnote.data.repository.NoteRepositoryImpl
import com.example.compnote.data.storage.NoteStorage
import com.example.compnote.data.storage.room.NoteRoomDao
import com.example.compnote.data.storage.room.NoteStorageRoomImpl
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
    fun provideNoteStorage(noteRoomDao: NoteRoomDao): NoteStorage {
        return NoteStorageRoomImpl(noteRoomDao = noteRoomDao)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteStorage: NoteStorage): NoteRepository {
        return NoteRepositoryImpl(noteStorage = noteStorage)
    }
}