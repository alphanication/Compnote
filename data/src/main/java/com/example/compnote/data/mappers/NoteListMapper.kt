package com.example.compnote.data.mappers

import com.example.compnote.data.data_source.locale.models.NoteEntity
import com.example.compnote.domain.models.Note

class NoteListMapper : BaseMapper<List<NoteEntity>, List<Note>> {

    override fun mapFromEntity(type: List<NoteEntity>): List<Note> =
        type.map { noteEntity ->
            Note(
                id = noteEntity.id,
                title = noteEntity.title,
                subtitle = noteEntity.subtitle
            )
        }

    override fun mapToEntity(type: List<Note>): List<NoteEntity> =
        type.map { note ->
            NoteEntity(
                id = note.id,
                title = note.title,
                subtitle = note.subtitle
            )
        }
}