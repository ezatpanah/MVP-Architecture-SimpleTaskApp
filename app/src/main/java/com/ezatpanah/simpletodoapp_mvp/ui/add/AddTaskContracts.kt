package com.ezatpanah.simpletodoapp_mvp.ui.add

import com.ezatpanah.simpletodoapp_mvp.db.TaskEntity
import com.ezatpanah.simpletodoapp_mvp.ui.base.BasePresenter

interface AddTaskContracts {
    interface View {
        fun close()
        fun loadTaskData(entity: TaskEntity)
    }

    interface Presenter : BasePresenter {
        fun saveTask(entity: TaskEntity)
        fun detailsTask(id: Int)
        fun updateTask(entity: TaskEntity)
    }
}