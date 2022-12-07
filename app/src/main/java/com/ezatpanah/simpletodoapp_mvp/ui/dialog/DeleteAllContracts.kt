package com.ezatpanah.simpletodoapp_mvp.ui.dialog

import com.ezatpanah.simpletodoapp_mvp.ui.base.BasePresenter

interface DeleteAllContracts {
    interface View {
        fun deleteMessage()
    }

    interface Presenter : BasePresenter {
        fun deleteAllTask()
    }
}