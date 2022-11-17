package com.ezatpanah.simplenoteapp_mvp.di

import android.content.Context
import android.content.Entity
import androidx.room.Room
import com.ezatpanah.simplenoteapp_mvp.db.NoteDatabase
import com.ezatpanah.simplenoteapp_mvp.db.NoteEntity
import com.ezatpanah.simplenoteapp_mvp.utils.NOTE_DATABASE
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
        context, NoteDatabase::class.java, NOTE_DATABASE )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db : NoteDatabase) = db.noteDao()

    @Provides
    @Singleton
    fun provideEntity() = NoteEntity()

}