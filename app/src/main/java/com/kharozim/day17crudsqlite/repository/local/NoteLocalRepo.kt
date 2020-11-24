package com.kharozim.day17crudsqlite.repository.local

import android.content.ContentValues
import android.content.Context
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.kharozim.day17crudsqlite.models.NoteModel
import com.kharozim.day17crudsqlite.repository.NoteRepsitory
import com.kharozim.day17crudsqlite.repository.local.databases.LocaleDB
import com.kharozim.day17crudsqlite.repository.local.entities.NoteEntity

class NoteLocalRepo(context: Context) : NoteRepsitory {

    private val localDB by lazy { LocaleDB(context) }

    override fun getAllNote(): List<NoteModel> {
        val db = localDB.readableDatabase
        val sort = "${NoteEntity.COLLUM_ID} DESC"
        val cursor = db.query(
            NoteEntity.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sort
        )

        val note = mutableListOf<NoteModel>()

        while (cursor.moveToNext()) {
            val id = cursor.getLongOrNull(cursor.getColumnIndexOrThrow(NoteEntity.COLLUM_ID))
            val task =
                cursor.getStringOrNull(cursor.getColumnIndexOrThrow(NoteEntity.COLLUM_CATATAN))
            val status =
                cursor.getIntOrNull(cursor.getColumnIndexOrThrow(NoteEntity.COLLUM_STATUS)) ?: 0

            if (id != null && task != null) {
                val noteModel = NoteModel(id, task, status == 1)
                note.add(noteModel)
            }
        }
        cursor.close()
        return note
    }

    override fun deleteNote(id: Long): Long {
        val db = localDB.writableDatabase
        val selection = "${NoteEntity.COLLUM_ID}"
        val selectionArgs = arrayOf("$id")

        db.delete(NoteEntity.TABLE_NAME, selection, selectionArgs)
        db.close()
        return id
    }

    override fun updateNote(noteModel: NoteModel): NoteModel {
        val db = localDB.writableDatabase
        val value = ContentValues().apply {
            put(NoteEntity.COLLUM_CATATAN, noteModel.catatan)
            put(
                NoteEntity.COLLUM_STATUS,
                if (noteModel.status) 1 else 0
            )
        }
        val selection = "${NoteEntity.COLLUM_ID} = ?"
        val selectionArgs = arrayOf("${noteModel.id}")

        db.update(NoteEntity.TABLE_NAME, value, selection, selectionArgs)
        db.close()
        return noteModel
    }

    override fun insertNote(task: String): NoteModel {
        val db = localDB.writableDatabase
        val value = ContentValues().apply {
            put(NoteEntity.COLLUM_CATATAN, task)
            put(NoteEntity.COLLUM_STATUS, 0)
        }
        val id = db.insert(NoteEntity.TABLE_NAME, NoteEntity.COLLUM_ID, value)
        db.close()
        return NoteModel(id, task)
    }


}