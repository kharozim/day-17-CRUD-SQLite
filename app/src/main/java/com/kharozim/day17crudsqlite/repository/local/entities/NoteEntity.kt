package com.kharozim.day17crudsqlite.repository.local.entities

class NoteEntity(val id: Long, val catatan: String) {
    companion object {
        const val TABLE_NAME = "note_table"
        const val COLLUM_ID = "id"
        const val COLLUM_CATATAN = "task"
        const val COLLUM_STATUS = "status"
        const val SQL_CREATE_NOTE = "CREATE TABLE $TABLE_NAME (" +
                "$COLLUM_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLLUM_CATATAN TEXT, " +
                "$COLLUM_STATUS INTEGER)"
        const val SQL_DELETE_NOTE = "DROP TABLE IF EXISTS $TABLE_NAME"
        const val SQL_MIGRATE_NOTE = "ALTER TABLE $TABLE_NAME ADD COLUMN $COLLUM_STATUS INTEGER"
    }
}