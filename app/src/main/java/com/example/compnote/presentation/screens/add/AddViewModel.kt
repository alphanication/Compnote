package com.example.compnote.presentation.screens.add

import androidx.lifecycle.ViewModel
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Response
import com.example.compnote.domain.usecase.NoteAddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val noteAddUseCase: NoteAddUseCase,
) : ViewModel() {

    fun addNote(note: Note): Flow<Boolean> = flow {
        noteAddUseCase.execute(note = note).collect { response ->
            when (response) {
                is Response.Loading -> {}
                is Response.Fail -> emit(false)
                is Response.Success -> emit(response.data)
            }
        }
    }.flowOn(Dispatchers.IO)
}