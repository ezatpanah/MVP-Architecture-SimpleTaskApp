package com.ezatpanah.simpletodoapp_mvp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ezatpanah.simpletodoapp_mvp.utils.Constants.TASK_TABLE
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTask(entity: TaskEntity) : Completable

    @Delete
    fun deleteTask(entity: TaskEntity) : Completable

    @Update
    fun updateTask (entity: TaskEntity) : Completable

    @Query("SELECT * FROM $TASK_TABLE")
    fun getAllTasks(): Observable<List<TaskEntity>>

    @Query("DELETE FROM $TASK_TABLE")
    fun deleteAllTask() : Completable

    @Query("SELECT * FROM $TASK_TABLE WHERE id ==:id")
    fun getTask(id:Int) :Observable<TaskEntity>

    @Query("SELECT * FROM $TASK_TABLE WHERE pr == :priority")
    fun filterTask(priority: String): Observable<List<TaskEntity>>

    @Query("SELECT * FROM $TASK_TABLE WHERE title LIKE '%' || :title || '%' ")
    fun searchTask(title: String): Observable<List<TaskEntity>>

}