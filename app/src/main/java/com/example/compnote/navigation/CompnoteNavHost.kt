package com.example.compnote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compnote.MainViewModel
import com.example.compnote.screens.AddScreen
import com.example.compnote.screens.MainScreen
import com.example.compnote.screens.NoteScreen
import com.example.compnote.util.Constants

@Composable
fun CompnoteNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.MainScreen.route) {

        composable(route = NavRoute.MainScreen.route) {
            MainScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(route = NavRoute.AddScreen.route) {
            AddScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(route = NavRoute.NoteScreen.route + "/{${Constants.Keys.NOTE_ID}}") { backStackEntry ->
            NoteScreen(
                navController = navController,
                viewModel = mViewModel,
                noteId = backStackEntry.arguments?.getString(Constants.Keys.NOTE_ID)
            )
        }
    }
}