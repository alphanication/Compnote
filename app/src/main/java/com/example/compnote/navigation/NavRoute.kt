package com.example.compnote.navigation

import com.example.compnote.util.Constants

sealed class NavRoute(val route: String) {
    object MainScreen : NavRoute(route = Constants.Screens.MAIN_SCREEN)
    object AddScreen : NavRoute(route = Constants.Screens.ADD_SCREEN)
    object NoteScreen : NavRoute(route = Constants.Screens.NOTE_SCREEN)
}