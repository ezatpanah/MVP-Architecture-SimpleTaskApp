package com.ezatpanah.simpletodoapp_mvp.di

import android.app.Activity
import com.ezatpanah.simpletodoapp_mvp.ui.main.MainContracts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ContractActivityModule {

    @Provides
    fun mainView(activity: Activity):MainContracts.View{
        return activity as MainContracts.View
    }

}