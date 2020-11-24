package com.kharozim.day17crudsqlite.repository

import com.kharozim.day17crudsqlite.models.NoteModel

interface NoteRepsitory {
    fun getAllNote(): List<NoteModel>
    fun deleteNote(id: Long): Long
    fun updateNote(noteModel: NoteModel): NoteModel
    fun insertNote(task: String): NoteModel
}