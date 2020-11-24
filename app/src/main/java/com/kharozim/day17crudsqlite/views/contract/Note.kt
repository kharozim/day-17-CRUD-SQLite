package com.kharozim.day17crudsqlite.views.contract

import com.kharozim.day17crudsqlite.models.NoteModel

interface Note {

    interface View {
        fun onSuccessGetAllNote(note: List<NoteModel>)
        fun onSuccessUpdateNote(noteModel: NoteModel)
        fun onSuccessInsertNote(noteModel: NoteModel)
        fun onSuccessDeleteNote(id: Long)
    }

    interface Presenter {
        fun getAllNote()
        fun updateNote(noteModel: NoteModel)
        fun insertNote(task: String)
        fun deleteNote(id: Long)
    }
}