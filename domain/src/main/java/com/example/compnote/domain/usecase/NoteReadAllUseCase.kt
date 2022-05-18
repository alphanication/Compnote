package com.example.compnote.domain.usecase

import com.example.compnote.domain.models.Note
import com.example.compnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteReadAllUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(): Flow<List<Note>> {
        return noteRepository.readAll()
    }
}