package com.example.compnote.presentation.screens.add

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.compnote.domain.models.Note
import com.example.compnote.presentation.navigation.NavRoute
import com.example.compnote.presentation.util.Constants
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
                modifier = Modifier.padding(bottom = 15.dp, end = 15.dp),
                onClick = {
                    if (title.isNotEmpty() and subtitle.isNotEmpty()) {
                        coroutineScope.launch {
                            mViewModel.addNote(note = Note(title = title, subtitle = subtitle))
                                .collect {
                                    if (it) navController.navigate(NavRoute.MainScreen.route)
                                }
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
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
            Spacer(modifier = Modifier.padding(top = 15.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 15.dp),
                value = title,
                onValueChange = { title = it },
                label = { Text(text = Constants.Keys.TITLE) },
                trailingIcon = {
                    if (title.isEmpty()) Icon(Icons.Filled.Info, contentDescription = "error", tint = Color.Red)
                },
                isError = title.isEmpty(),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(15.dp)
            )

            Spacer(modifier = Modifier.padding(top = 5.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(200.dp)
                    .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text(text = Constants.Keys.SUBTITLE) },
                isError = subtitle.isEmpty(),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(15.dp)
            )
        }
    }
}