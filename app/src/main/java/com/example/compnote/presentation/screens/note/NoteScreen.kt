package com.example.compnote.presentation.screens.note

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.example.compnote.presentation.util.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavController, noteId: String?) {

    val mViewModel = hiltViewModel<NoteViewModel>()

    val note = mViewModel.note.observeAsState(
        Note(
            title = Constants.Keys.NONE,
            subtitle = Constants.Keys.NONE
        )
    ).value

    LaunchedEffect(key1 = Unit, block = {
        if (noteId?.isNotEmpty() == true) mViewModel.getNoteById(id = noteId.toInt())
    })

    val coroutineScope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    var title by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var subtitle by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var isButtonEnabled by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetBackgroundColor = Color(0xFFBDBFBF),
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(25.dp)
                ) {
                    Text(
                        text = Constants.Keys.EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    OutlinedTextField(
                        value = title,
                        onValueChange = {
                            title = it
                            isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                        },
                        label = { Text(text = Constants.Keys.TITLE) },
                        isError = title.isEmpty(),
                        shape = RoundedCornerShape(25.dp)
                    )

                    Spacer(modifier = Modifier.padding(top = 5.dp))

                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = {
                            subtitle = it
                            isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                        },
                        label = { Text(text = Constants.Keys.SUBTITLE) },
                        isError = subtitle.isEmpty(),
                        shape = RoundedCornerShape(25.dp)
                    )

                    Spacer(modifier = Modifier.padding(top = 15.dp))

                    Button(
                        enabled = isButtonEnabled,
                        onClick = {
                            coroutineScope.launch {
                                mViewModel.updateNote(
                                    note = Note(
                                        id = note.id,
                                        title = title,
                                        subtitle = subtitle
                                    )
                                )
                                    .collect {
                                        if (it) bottomSheetState.hide()
                                    }
                            }
                        },
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(text = Constants.Keys.UPDATE_NOTE)
                    }
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
                backgroundColor = Color(0xFFBDBFBF),
                shape = RoundedCornerShape(25.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Spacer(modifier = Modifier.padding(top = 10.dp))

                        Text(
                            text = note.title,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 5.dp)
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
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = Constants.Keys.UPDATE)
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            mViewModel.deleteNoteById(id = note.id)
                                .collect {
                                    if (it) navController.navigate(NavRoute.MainScreen.route)
                                }

                        }
                    },
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = Constants.Keys.DELETE)
                }
            }
        }
    }

}