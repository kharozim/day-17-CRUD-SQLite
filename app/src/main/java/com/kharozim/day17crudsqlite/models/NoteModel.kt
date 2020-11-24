package com.kharozim.day17crudsqlite.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteModel (val id : Long, val catatan : String, var status : Boolean) : Parcelable
