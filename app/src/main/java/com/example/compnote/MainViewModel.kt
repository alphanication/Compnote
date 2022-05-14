package com.example.compnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compnote.models.Note
import com.example.compnote.util.TYPE_FIREBASE
import com.example.compnote.util.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readTest = MutableLiveData<List<Note>>()

    private val dbType = MutableLiveData(TYPE_ROOM)

    init {
        readTest.value = when (dbType.value) {
            TYPE_ROOM -> listOf(
                Note(title = "Note 1", subtitle = "Subtitle for note 1"),
                Note(title = "Note 2", subtitle = "Subtitle for note 2"),
                Note(title = "Note 3", subtitle = "Subtitle for note 3")
            )
            TYPE_FIREBASE -> listOf()
            else -> listOf()
        }
    }

    fun initDatabase(type: String) {
        dbType.value = type
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}