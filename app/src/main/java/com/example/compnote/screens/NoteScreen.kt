package com.example.compnote.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compnote.MainViewModel
import com.example.compnote.models.Note
import com.example.compnote.util.Constants

@Composable
fun NoteScreen(navController: NavController, viewModel: MainViewModel, noteId: String?) {

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull { it.id == noteId?.toInt() } ?: Note(
        title = Constants.Keys.NONE,
        subtitle = Constants.Keys.NONE
    )

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = note?.title,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = note?.subtitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}