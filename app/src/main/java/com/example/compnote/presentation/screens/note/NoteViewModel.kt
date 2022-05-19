package com.example.compnote.presentation.screens.note

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Response
import com.example.compnote.domain.usecase.NoteDeleteByIdUseCase
import com.example.compnote.domain.usecase.NoteGetByIdUseCase
import com.example.compnote.domain.usecase.NoteUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteDeleteByIdUseCase: NoteDeleteByIdUseCase,
    private val noteUpdateUseCase: NoteUpdateUseCase,
    private val noteGetByIdUseCase: NoteGetByIdUseCase
): ViewModel() {

    val note = MutableLiveData<Note>()

    fun getNoteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            noteGetByIdUseCase.execute(id = id).collect { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Fail -> {}
                    is Response.Success -> this@NoteViewModel.note.postValue(response.data)
                }
            }
        }
    }

    fun updateNote(note: Note): Flow<Boolean> = flow {
        noteUpdateUseCase.execute(note = note).collect { response ->
            when (response) {
                is Response.Loading -> {}
                is Response.Fail -> emit(false)
                is Response.Success -> emit(response.data)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun deleteNoteById(id: Int): Flow<Boolean> = flow<Boolean> {
        noteDeleteByIdUseCase.execute(id = id).collect { response ->
            when (response) {
                is Response.Loading -> {}
                is Response.Fail -> emit(false)
                is Response.Success -> emit(response.data)
            }
        }
    }.flowOn(Dispatchers.IO)
}