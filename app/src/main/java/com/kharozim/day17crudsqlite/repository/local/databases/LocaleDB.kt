package com.kharozim.day17crudsqlite.repository.local.databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import com.kharozim.day17crudsqlite.repository.local.entities.NoteEntity

class LocaleDB(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object{
        const val DATABASE_NAME = "note.db"
        const val DATABASE_VERSION = 1

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(NoteEntity.SQL_CREATE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//if migrate DB Local

//        when (newVersion){
//            1 -> {
//                db?.execSQL(NoteEntity.SQL_DELETE_NOTE)
//                db?.execSQL(NoteEntity.SQL_CREATE_NOTE)
//            }
//            2-> db?.execSQL(NoteEntity.SQL_MIGRATE_NOTE)
//        }
    }
}