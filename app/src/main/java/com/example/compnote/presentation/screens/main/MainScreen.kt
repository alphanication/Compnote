package com.example.compnote.presentation.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.compnote.domain.models.Note
import com.example.compnote.presentation.navigation.NavRoute
import com.example.compnote.presentation.ui.theme.Blue400
import com.example.compnote.presentation.util.Constants


@Composable
fun MainScreen(navController: NavController) {
    val mViewModel = hiltViewModel<MainViewModel>()
    val focusManager = LocalFocusManager.current

    val notes = mViewModel.allListNote.observeAsState(listOf()).value
    var searchText by remember { mutableStateOf(Constants.Keys.EMPTY) }

    LaunchedEffect(key1 = searchText, block = {
        mViewModel.searchByTitle(title = searchText)
    })

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
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = { searchText = it },
                shape = RoundedCornerShape(15.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "textfield search")
                },
                singleLine = true,
                keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                label = { Text(text = Constants.Keys.SEARCH) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue400,
                    disabledBorderColor = Blue400,
                    unfocusedBorderColor = Blue400
                )
            )

            Spacer(modifier = Modifier.padding(top = 15.dp))

            LazyColumn {
                items(notes) { note ->
                    NoteItem(navController = navController, note = note)
                }
            }
        }
    }
}

@Composable
fun NoteItem(navController: NavController, note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .clickable {
                navController.navigate(NavRoute.NoteScreen.route + "/${note.id}")
            },
        shape = CutCornerShape(topEnd = 20.dp, bottomStart = 20.dp),
        border = BorderStroke(2.dp, MaterialTheme.colors.primary)
    ) {
        Column(
            modifier = Modifier
                .heightIn(0.dp, 200.dp)
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = note.title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            Spacer(modifier = Modifier.padding(top = 5.dp))

            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = note.subtitle,
                fontSize = 18.sp,
                maxLines = 7
            )

            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
    }
}