package com.example.compnote.util

import com.example.compnote.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

object Constants {
    object Keys {
        const val NOTES_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "ADD NEW NOTE"
        const val NOTE_TITLE = "Note TITLE"
        const val NOTE_SUBTITLE = "Note SUBTITLE"
        const val ADD_NOTE = "ADD NOTE"
        const val WHAT_WILL_WE_USE = "What will we use?"
        const val ROOM_DATABASE = "ROOM DATABASE"
        const val FIREBASE_DATABASE = "FIREBASE DATABASE"
        const val ID = "id"
        const val NONE = "none"
        const val UPDATE = "UPDATE"
        const val DELETE = "DELETE"
        const val BACK = "BACK"
    }

    object Screens {
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
    }
}