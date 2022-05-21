package com.example.compnote.presentation.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.compnote.domain.models.Note
import com.example.compnote.presentation.navigation.NavRoute


@Composable
fun MainScreen(navController: NavController) {
    val mViewModel = hiltViewModel<MainViewModel>()

    val notes = mViewModel.allListNote.observeAsState(listOf()).value

    DisposableEffect(Unit) {
        mViewModel.getAllNotes()

        onDispose { mViewModel.clearAllListNoteLiveData() }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.AddScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add icons",
                    tint = Color.White
                )
            }
        },
        modifier = Modifier.padding(bottom = 8.dp, end = 8.dp)
    ) {
        LazyColumn {
            items(notes) { note ->
                NoteItem(navController = navController, note = note)
            }
        }
    }
}

@Composable
fun NoteItem(navController: NavController, note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.NoteScreen.route + "/${note.id}")
            },
        shape = CutCornerShape(topEnd = 20.dp, bottomStart = 20.dp),
        border = BorderStroke(2.dp, MaterialTheme.colors.primary)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(text = note.subtitle, modifier = Modifier.padding(horizontal = 10.dp))

            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
    }
}