package com.ezatpanah.simplenoteapp_mvp.ui.add

import com.ezatpanah.simplenoteapp_mvp.db.NoteEntity
import com.ezatpanah.simplenoteapp_mvp.ui.base.BasePresenter

interface NoteContracts {
    interface View {
        fun close()
    }

    interface Presenter : BasePresenter {
        fun saveNote(entity: NoteEntity)
    }
}