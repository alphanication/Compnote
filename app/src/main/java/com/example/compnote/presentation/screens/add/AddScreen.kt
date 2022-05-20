package com.example.compnote.presentation.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.*
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
import com.example.compnote.presentation.util.Constants
import com.example.compnote.presentation.util.Constants.Keys.ADD_NOTE
import kotlinx.coroutines.launch

@Composable
fun AddScreen(navController: NavController) {
    val mViewModel = hiltViewModel<AddViewModel>()

    val coroutineScope = rememberCoroutineScope()

    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        mViewModel.addNote(note = Note(title = title, subtitle = subtitle))
                            .collect {
                                if (it) navController.navigate(NavRoute.MainScreen.route)
                            }
                    }
                },
                backgroundColor = Color.Blue,
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Add note",
                    tint = Color.White
                )
            }
        },
        modifier = Modifier.padding(bottom = 8.dp, end = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = Constants.Keys.ADD_NEW_NOTE,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = Constants.Keys.NOTE_TITLE) },
                isError = title.isEmpty()
            )

            OutlinedTextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text(text = Constants.Keys.NOTE_SUBTITLE) },
                isError = subtitle.isEmpty()
            )
        }
    }
}