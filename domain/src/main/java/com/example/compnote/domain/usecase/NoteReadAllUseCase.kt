package com.example.compnote.domain.usecase

import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Resource
import com.example.compnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteReadAllUseCase(
    private val noteRepository: NoteRepository
) {

    suspend fun execute(): Flow<Resource<List<Note>>> =
        noteRepository.readAll()
}