package com.example.compnote.presentation.screens.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Resource
import com.example.compnote.domain.usecase.NoteAddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val noteAddUseCase: NoteAddUseCase,
) : ViewModel() {

    private val _addNoteResult = MutableLiveData<Boolean>()
    val addNoteResult: LiveData<Boolean> = _addNoteResult

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteAddUseCase.execute(note = note).collect { response ->
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Fail -> _addNoteResult.postValue(false)
                    is Resource.Success -> _addNoteResult.postValue(true)
                }
            }
        }
    }
}