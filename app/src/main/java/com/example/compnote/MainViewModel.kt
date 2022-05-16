package com.example.compnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compnote.database.room.repository.DatabaseRepositoryRoomImpl
import com.example.compnote.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val databaseRepositoryRoomImpl: DatabaseRepositoryRoomImpl
) : ViewModel() {
    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepositoryRoomImpl.add(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepositoryRoomImpl.update(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepositoryRoomImpl.delete(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun readAllNotes() = databaseRepositoryRoomImpl.readAll
}