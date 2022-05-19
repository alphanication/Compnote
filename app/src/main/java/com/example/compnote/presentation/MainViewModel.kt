package com.example.compnote.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compnote.domain.models.Note
import com.example.compnote.domain.models.Response
import com.example.compnote.domain.usecase.*
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
) : ViewModel() {

    val allListNote = MutableLiveData<List<Note>>()

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteReadAllUseCase.execute().collect { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Fail -> {}
                    is Response.Success -> this@MainViewModel.allListNote.postValue(response.data)
                }
            }
        }
    }
}