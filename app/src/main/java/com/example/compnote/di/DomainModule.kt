package com.example.compnote.di

import com.example.compnote.domain.repository.NoteRepository
import com.example.compnote.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideNoteAddUseCase(noteRepository: NoteRepository): NoteAddUseCase {
        return NoteAddUseCase(noteRepository = noteRepository)
    }

    @Provides
    fun provideNoteDeleteUseCase(noteRepository: NoteRepository): NoteDeleteUseCase {
        return NoteDeleteUseCase(noteRepository = noteRepository)
    }

    @Provides
    fun provideNoteReadAllUseCase(noteRepository: NoteRepository): NoteReadAllUseCase {
        return NoteReadAllUseCase(noteRepository = noteRepository)
    }

    @Provides
    fun provideNoteUpdateUseCase(noteRepository: NoteRepository): NoteUpdateUseCase {
        return NoteUpdateUseCase(noteRepository = noteRepository)
    }

    @Provides
    fun provideNoteGetByIdUseCase(noteRepository: NoteRepository) : NoteGetByIdUseCase {
        return NoteGetByIdUseCase(noteRepository = noteRepository)
    }
}