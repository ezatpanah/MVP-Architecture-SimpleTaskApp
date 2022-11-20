package com.ezatpanah.simplenoteapp_mvp.repository

import com.ezatpanah.simplenoteapp_mvp.db.NoteDao
import com.ezatpanah.simplenoteapp_mvp.db.NoteEntity
import javax.inject.Inject

class DbRepository  @Inject constructor(private val dao : NoteDao){
    fun saveNote (entity: NoteEntity) = dao .saveNote(entity)
    fun loadAllNotes() = dao.getAllNotes()
    fun deleteNote(entity: NoteEntity) = dao.deleteNote(entity)
    fun filterNote(priority: String) = dao.filterNote(priority)
    fun searchNote(title: String) = dao.searchNote(title)
}