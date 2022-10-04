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
    fun provideNoteAddUseCase(noteRepository: NoteRepository): NoteAddUseCase =
        NoteAddUseCase(noteRepository)

    @Provides
    fun provideNoteDeleteByIdUseCase(noteRepository: NoteRepository): NoteDeleteByIdUseCase =
        NoteDeleteByIdUseCase(noteRepository)

    @Provides
    fun provideNoteReadAllUseCase(noteRepository: NoteRepository): NoteReadAllUseCase =
        NoteReadAllUseCase(noteRepository)

    @Provides
    fun provideNoteUpdateUseCase(noteRepository: NoteRepository): NoteUpdateUseCase =
        NoteUpdateUseCase(noteRepository)

    @Provides
    fun provideNoteGetByIdUseCase(noteRepository: NoteRepository): NoteGetByIdUseCase =
        NoteGetByIdUseCase(noteRepository)

    @Provides
    fun provideNoteSearchByTitleUseCase(noteRepository: NoteRepository): NoteSearchByTitleUseCase =
        NoteSearchByTitleUseCase(noteRepository)
}