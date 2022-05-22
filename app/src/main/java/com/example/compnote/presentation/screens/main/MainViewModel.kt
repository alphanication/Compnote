package com.example.compnote.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Response
import com.example.compnote.domain.usecase.NoteReadAllUseCase
import com.example.compnote.domain.usecase.NoteSearchByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteReadAllUseCase: NoteReadAllUseCase,
    private val searchByTitleUseCase: NoteSearchByTitleUseCase
) : ViewModel() {

    private val _allListNote = MutableLiveData<List<Note>>()
    val allListNote: LiveData<List<Note>> = _allListNote

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteReadAllUseCase.execute().collect { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Fail -> {}
                    is Response.Success -> {
                        // this: reversed() so that the latest notes are displayed first
                        this@MainViewModel._allListNote.postValue(response.data.reversed())
                    }
                }
            }
        }
    }

    fun searchByTitle(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchByTitleUseCase.execute(title = title).collect { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Fail -> {}
                    is Response.Success -> {
                        // this: reversed() so that the latest notes are displayed first
                        this@MainViewModel._allListNote.postValue(response.data.reversed())
                    }
                }
            }
        }
    }

    fun clearAllListNoteLiveData() {
        _allListNote.value = listOf()
    }
}