package com.example.compnote

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(application: Application) : AndroidViewModel(application) {
    fun initDatabase() {
        Log.d("alpha33", "vm main init")
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