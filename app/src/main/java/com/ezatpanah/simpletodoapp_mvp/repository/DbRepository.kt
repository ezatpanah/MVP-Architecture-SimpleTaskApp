package com.ezatpanah.simpletodoapp_mvp.repository

import com.ezatpanah.simpletodoapp_mvp.db.TaskDao
import com.ezatpanah.simpletodoapp_mvp.db.TaskEntity
import javax.inject.Inject

class DbRepository  @Inject constructor(private val dao : TaskDao){
    fun saveTask (entity: TaskEntity) = dao .saveTask(entity)
    fun loadAllTasks() = dao.getAllTasks()
    fun deleteTask(entity: TaskEntity) = dao.deleteTask(entity)
    fun deleteAllTask() = dao.deleteAllTask()
    fun detailsTask(id:Int)= dao.getTask(id)
    fun updateTask(entity: TaskEntity)= dao.updateTask(entity)
    fun filterTask(priority: String) = dao.filterTask(priority)
    fun searchTask(title: String) = dao.searchTask(title)
}