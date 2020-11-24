package com.kharozim.day17crudsqlite.presenter

import com.kharozim.day17crudsqlite.models.NoteModel
import com.kharozim.day17crudsqlite.repository.NoteRepsitory
import com.kharozim.day17crudsqlite.views.contract.Note

class NotePresenter(private val view: Note.View, private val repository: NoteRepsitory) :
    Note.Presenter {
    override fun getAllNote() {
        val noteLIst by lazy { repository.getAllNote() }
        view.onSuccessGetAllNote(noteLIst)
    }

    override fun updateNote(noteModel: NoteModel) {
        val note by lazy { repository.updateNote(noteModel) }
        view.onSuccessUpdateNote(note)
    }

    override fun insertNote(task: String) {
        val note by lazy { repository.insertNote(task) }
        view.onSuccessInsertNote(note)
    }

    override fun deleteNote(id: Long) {
        val noteId by lazy { repository.deleteNote(id) }
        view.onSuccessDeleteNote(noteId)
    }
}