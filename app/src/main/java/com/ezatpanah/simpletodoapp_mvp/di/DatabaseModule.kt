package com.ezatpanah.simpletodoapp_mvp.di

import android.content.Context
import androidx.room.Room
import com.ezatpanah.simpletodoapp_mvp.db.TaskDatabase
import com.ezatpanah.simpletodoapp_mvp.db.TaskEntity
import com.ezatpanah.simpletodoapp_mvp.utils.Constants.TASK_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, TaskDatabase::class.java, TASK_DATABASE
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: TaskDatabase) = db.noteDao()

    @Provides
    @Singleton
    fun provideEntity() = TaskEntity()

}