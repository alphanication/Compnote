package com.example.compnote.presentation.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compnote.domain.models.Note
import com.example.compnote.presentation.navigation.NavRoute

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