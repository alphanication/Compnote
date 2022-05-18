package com.example.compnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.usecase.NoteAddUseCase
import com.example.compnote.domain.usecase.NoteDeleteUseCase
import com.example.compnote.domain.usecase.NoteReadAllUseCase
import com.example.compnote.domain.usecase.NoteUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteReadAllUseCase: NoteReadAllUseCase,
    private val noteDeleteUseCase: NoteDeleteUseCase,
    private val noteAddUseCase: NoteAddUseCase,
    private val noteUpdateUseCase: NoteUpdateUseCase
) : ViewModel() {

    val allListNote = MutableLiveData<List<Note>>()

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteReadAllUseCase.execute().collect { listNote ->
                allListNote.postValue(listNote)
            }
        }
    }

    fun addNote(note: Note): Flow<Boolean> = flow {
        noteAddUseCase.execute(note = note).collect { emit(it) }
    }.flowOn(Dispatchers.IO)

    fun updateNote(note: Note): Flow<Boolean> = flow<Boolean> {
        noteUpdateUseCase.execute(note = note).collect { emit(it) }
    }.flowOn(Dispatchers.IO)

    fun deleteNote(note: Note): Flow<Boolean> = flow<Boolean> {
        noteDeleteUseCase.execute(note = note).collect { emit(it) }
    }.flowOn(Dispatchers.IO)
}