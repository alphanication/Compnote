package com.example.compnote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compnote.screens.Add
import com.example.compnote.screens.Main
import com.example.compnote.screens.Note
import com.example.compnote.screens.Start

sealed class NavRoute(val route: String) {
    object Start : NavRoute(route = "start_screen")
    object Main : NavRoute(route = "main_screen")
    object Add : NavRoute(route = "add_screen")
    object Note : NavRoute(route = "note_screen")
}

@Composable
fun CompnoteNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { Start(navController = navController) }
        composable(NavRoute.Main.route) { Main(navController = navController) }
        composable(NavRoute.Add.route) { Add(navController = navController) }
        composable(NavRoute.Note.route) { Note(navController = navController) }
    }
}