package com.example.compnote.domain.usecase

import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Response
import com.example.compnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteUpdateUseCase(
    private val noteRepository: NoteRepository
) {

    suspend fun execute(note: Note): Flow<Response<Boolean>> =
        noteRepository.update(note)
}