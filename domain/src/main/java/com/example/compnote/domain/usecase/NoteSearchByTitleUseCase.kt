package com.example.compnote.domain.usecase

import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Resource
import com.example.compnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteSearchByTitleUseCase(
    private val noteRepository: NoteRepository
) {

    suspend fun execute(title: String): Flow<Resource<List<Note>>> =
        noteRepository.searchByTitle(title)
}