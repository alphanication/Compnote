package com.example.compnote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compnote.MainViewModel
import com.example.compnote.screens.*
import com.example.compnote.util.Constants

sealed class NavRoute(val route: String) {
    object Start : NavRoute(route = Constants.Screens.START_SCREEN)
    object Main : NavRoute(route = Constants.Screens.MAIN_SCREEN)
    object Add : NavRoute(route = Constants.Screens.ADD_SCREEN)
    object Note : NavRoute(route = Constants.Screens.NOTE_SCREEN)
}

@Composable
fun CompnoteNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Note.route) { NoteScreen(navController = navController, viewModel = mViewModel) }
    }
}