package com.example.compnote.domain.usecase

import com.example.compnote.domain.models.Resource
import com.example.compnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteDeleteByIdUseCase(
    private val noteRepository: NoteRepository
) {

    suspend fun execute(id: Int): Flow<Resource<Boolean>> =
        noteRepository.deleteNoteById(id)
}