package com.ezatpanah.simplenoteapp_mvp.ui.add

import com.ezatpanah.simplenoteapp_mvp.db.NoteEntity
import com.ezatpanah.simplenoteapp_mvp.ui.base.BasePresenter

interface NoteContracts {
    interface View {
        fun close()
        fun loadNoteData(entity: NoteEntity)
    }

    interface Presenter : BasePresenter {
        fun saveNote(entity: NoteEntity)
        fun detailsNote(id: Int)
        fun updateNote(entity: NoteEntity)
    }
}