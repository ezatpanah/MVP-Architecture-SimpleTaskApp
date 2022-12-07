package com.ezatpanah.simpletodoapp_mvp.ui.main

import com.ezatpanah.simpletodoapp_mvp.db.TaskEntity
import com.ezatpanah.simpletodoapp_mvp.ui.base.BasePresenter

interface MainContracts {

    interface View {
        fun showAllTasks(Tasks: List<TaskEntity>)
        fun showEmpty()
        fun deleteMessage()
    }

    interface Presenter : BasePresenter {
        fun loadAllTasks()
        fun deleteTask(entity: TaskEntity)
        fun filterTask(priority: String)
        fun searchTask(title: String)
    }
}