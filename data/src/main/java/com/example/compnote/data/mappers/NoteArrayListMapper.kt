package com.example.compnote.data.mappers

import com.example.compnote.data.storage.models.NoteEntity
import com.example.compnote.domain.models.Note

class NoteArrayListMapper : Mapper<List<NoteEntity>, List<Note>> {

    override fun mapFromEntity(type: List<NoteEntity>): List<Note> {
        val listNote = ArrayList<Note>()

        type.forEach { noteEntity ->
            listNote.add(
                Note(
                    id = noteEntity.id,
                    title = noteEntity.title,
                    subtitle = noteEntity.subtitle
                )
            )
        }

        return listNote.toList()
    }

    override fun mapToEntity(type: List<Note>): List<NoteEntity> {
        val listNote = ArrayList<NoteEntity>()

        type.forEach { note ->
            listNote.add(
                NoteEntity(
                    id = note.id,
                    title = note.title,
                    subtitle = note.subtitle
                )
            )
        }

        return listNote.toList()
    }
}