package com.ezatpanah.simplenoteapp_mvp.ui.main

import com.ezatpanah.simplenoteapp_mvp.db.NoteEntity
import com.ezatpanah.simplenoteapp_mvp.ui.base.BasePresenter

interface MainContracts {

    interface View {
        fun showAllNotes(notes: List<NoteEntity>)
        fun showEmpty()
        fun deleteMessage()
    }

    interface Presenter : BasePresenter {
        fun loadAllNotes()
        fun deleteNote(entity: NoteEntity)
        fun filterNote(priority: String)
        fun searchNote(title: String)
    }
}