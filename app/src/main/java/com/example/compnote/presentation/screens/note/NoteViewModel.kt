package com.example.compnote.presentation.screens.note

import android.util.Log
import androidx.lifecycle.LiveData
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteDeleteByIdUseCase: NoteDeleteByIdUseCase,
    private val noteUpdateUseCase: NoteUpdateUseCase,
    private val noteGetByIdUseCase: NoteGetByIdUseCase
) : ViewModel() {

    val note = MutableLiveData<Note>()

    private val _resultUpdateNote = MutableLiveData<Boolean>()
    val resultUpdateNote: LiveData<Boolean> = _resultUpdateNote

    private val _resultDeleteNote = MutableLiveData<Boolean>()
    val resultDeleteNote: LiveData<Boolean> = _resultDeleteNote

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            noteGetByIdUseCase.execute(id = id).collect { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Fail -> {}
                    is Response.Success -> {
                        Log.d("alpha33", "getNoteById: ${response.data.title}")
                        this@NoteViewModel.note.postValue(response.data)
                    }
                }
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteUpdateUseCase.execute(note = note).collect { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Fail -> _resultUpdateNote.postValue(false)
                    is Response.Success -> _resultUpdateNote.postValue(true)
                }
            }
        }
    }

    fun deleteNoteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDeleteByIdUseCase.execute(id = id).collect { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Fail -> {
                        Log.d("alpha33", "deleteNoteById fail: ${response.e}")
                        _resultDeleteNote.postValue(false)
                    }
                    is Response.Success -> {
                        Log.d("alpha33", "deleteNoteById success: ${response.data}")
                        _resultDeleteNote.postValue(true)
                    }
                }
            }
        }
    }
}