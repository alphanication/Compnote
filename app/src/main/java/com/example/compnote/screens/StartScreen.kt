package com.example.compnote.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compnote.MainViewModel
import com.example.compnote.navigation.NavRoute
import com.example.compnote.util.TYPE_FIREBASE
import com.example.compnote.util.TYPE_ROOM

@Composable
fun StartScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What will we use?")
            Button(
                onClick = {
                    viewModel.initDatabase(TYPE_ROOM) {
                        navController.navigate(route = NavRoute.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(8.dp)
            ) {
                Text(text = "ROOM DATABASE")
            }
            Button(
                onClick = {
                    viewModel.initDatabase(TYPE_FIREBASE) {
                        navController.navigate(route = NavRoute.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(8.dp)
            ) {
                Text(text = "FIREBASE DATABASE")
            }
        }
    }
}