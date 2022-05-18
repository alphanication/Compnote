package com.example.compnote.domain.usecase

import com.example.compnote.domain.models.Note
import com.example.compnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteGetByIdUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(id: Int) : Flow<Note> {
        return noteRepository.getNoteById(id = id)
    }
}