package com.example.compnote.data.mappers

import com.example.compnote.data.storage.models.NoteEntity
import com.example.compnote.domain.models.Note

class NoteMapper : BaseMapper<NoteEntity, Note> {
    override fun mapFromEntity(type: NoteEntity): Note =
        Note(
            id = type.id,
            title = type.title,
            subtitle = type.subtitle
        )

    override fun mapToEntity(type: Note): NoteEntity =
        NoteEntity(
            id = type.id,
            title = type.title,
            subtitle = type.subtitle
        )
}