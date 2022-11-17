package com.ezatpanah.simplenoteapp_mvp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ezatpanah.simplenoteapp_mvp.utils.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val desc: String = "",
    val cat: String = "",
    val pr: String = "",
)