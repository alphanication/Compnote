package com.example.compnote.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compnote.MainViewModel
import com.example.compnote.models.Note
import com.example.compnote.navigation.NavRoute
import com.example.compnote.util.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavController, viewModel: MainViewModel, noteId: String?) {

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull { it.id == noteId?.toInt() } ?: Note(
        title = Constants.Keys.NONE,
        subtitle = Constants.Keys.NONE
    )
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var subtitle by remember { mutableStateOf(Constants.Keys.EMPTY) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(
                        text = Constants.Keys.EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = Constants.Keys.TITLE) },
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = { subtitle = it },
                        label = { Text(text = Constants.Keys.SUBTITLE) },
                        isError = subtitle.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(note = Note(id = note.id, title = title, subtitle = subtitle)
                            ) {
                                navController.navigate(NavRoute.MainScreen.route)
                            }
                        }
                    ) {
                        Text(text = Constants.Keys.UPDATE_NOTE)
                    }
                }
            }
        }
    ) {
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
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = note.title,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Text(
                            text = note.subtitle,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                title = note.title
                                subtitle = note.subtitle
                                bottomSheetState.show()
                            }
                        }
                    ) {
                        Text(text = Constants.Keys.UPDATE)
                    }
                    Button(
                        onClick = {
                            viewModel.deleteNote(note = note) {
                                navController.navigate(NavRoute.MainScreen.route)
                            }
                        }
                    ) {
                        Text(text = Constants.Keys.DELETE)
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    onClick = {
                        navController.navigate(NavRoute.MainScreen.route)
                    }
                ) {
                    Text(text = Constants.Keys.BACK)
                }
            }
        }
    }
}