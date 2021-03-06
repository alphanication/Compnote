package com.example.compnote.presentation.screens.note

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
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
import com.example.compnote.presentation.ui.theme.backgroundContentButton
import com.example.compnote.presentation.util.Constants
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun NoteScreen(navController: NavController, noteId: String?) {

    val mViewModel = hiltViewModel<NoteViewModel>()

    val note = mViewModel.note.observeAsState(
        Note(
            title = Constants.Keys.NONE,
            subtitle = Constants.Keys.NONE
        )
    ).value

    val resultUpdateNote = mViewModel.resultUpdateNote.observeAsState(false).value
    val resultDeleteNote = mViewModel.resultDeleteNote.observeAsState(false).value

    LaunchedEffect(key1 = Unit, block = {
        if (noteId?.isNotEmpty() == true) mViewModel.getNoteById(id = noteId.toInt())
    })

    LaunchedEffect(key1 = resultUpdateNote, block = {
        if (resultUpdateNote) navController.navigate(NavRoute.MainScreen.route)
    })

    LaunchedEffect(key1 = resultDeleteNote, block = {
        if (resultDeleteNote) navController.navigate(NavRoute.MainScreen.route)
    })

    val coroutineScope = rememberCoroutineScope()

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    var title by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var subtitle by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var isButtonEnabled by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .padding(25.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .heightIn(0.dp, 100.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = Constants.Keys.EDIT_NOTE,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Button(
                            enabled = isButtonEnabled,
                            onClick = {
                                if (title.isNotEmpty() and subtitle.isNotEmpty()) {
                                    mViewModel.updateNote(
                                        note = Note(
                                            id = note.id,
                                            title = title,
                                            subtitle = subtitle
                                        )
                                    )
                                }
                            },
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(text = Constants.Keys.UPDATE_NOTE)
                        }
                    }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = title,
                        onValueChange = {
                            title = it
                            isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                        },
                        label = { Text(text = Constants.Keys.TITLE) },
                        trailingIcon = {
                            if (title.isEmpty()) Icon(
                                Icons.Filled.Info,
                                contentDescription = "error",
                                tint = Color.Red
                            )
                        },
                        isError = title.isEmpty(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface),
                        shape = RoundedCornerShape(25.dp)
                    )

                    Spacer(modifier = Modifier.padding(top = 5.dp))

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = subtitle,
                        onValueChange = {
                            subtitle = it
                            isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                        },
                        label = { Text(text = Constants.Keys.SUBTITLE) },
                        isError = subtitle.isEmpty(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface),
                        shape = RoundedCornerShape(25.dp)
                    )

                    Spacer(modifier = Modifier.padding(bottom = 15.dp))
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(top = 20.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp, 500.dp)
                    .padding(horizontal = 15.dp),
                backgroundColor = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(25.dp),
                border = BorderStroke(2.dp, MaterialTheme.colors.primary)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Spacer(modifier = Modifier.padding(top = 10.dp))

                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = note.title,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )

                        Spacer(modifier = Modifier.padding(top = 10.dp))

                        Text(
                            text = note.subtitle,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )

                        Spacer(modifier = Modifier.padding(top = 10.dp))
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(25.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            title = note.title
                            subtitle = note.subtitle
                            bottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = backgroundContentButton)
                ) {
                    Text(text = Constants.Keys.UPDATE)
                }

                Button(
                    onClick = {
                        mViewModel.deleteNoteById(id = note.id)
                    },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = backgroundContentButton)
                ) {
                    Text(text = Constants.Keys.DELETE)
                }
            }
        }
    }

}