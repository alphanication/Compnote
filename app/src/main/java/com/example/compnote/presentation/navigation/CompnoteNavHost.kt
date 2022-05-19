package com.example.compnote.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compnote.presentation.screens.add.AddScreen
import com.example.compnote.presentation.screens.main.MainScreen
import com.example.compnote.presentation.screens.note.NoteScreen
import com.example.compnote.presentation.util.Constants

@Composable
fun CompnoteNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.MainScreen.route) {

        composable(route = NavRoute.MainScreen.route) {
            MainScreen(
                navController = navController,
            )
        }
        composable(route = NavRoute.AddScreen.route) {
            AddScreen(
                navController = navController,
            )
        }
        composable(route = NavRoute.NoteScreen.route + "/{${Constants.Keys.NOTE_ID}}") { backStackEntry ->
            NoteScreen(
                navController = navController,
                noteId = backStackEntry.arguments?.getString(Constants.Keys.NOTE_ID)
            )
        }
    }
}