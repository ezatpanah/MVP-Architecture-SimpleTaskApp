package com.ezatpanah.simpletodoapp_mvp.di

import androidx.fragment.app.Fragment
import com.ezatpanah.simpletodoapp_mvp.ui.add.AddTaskContracts
import com.ezatpanah.simpletodoapp_mvp.ui.dialog.DeleteAllContracts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ContractFragmentModule {

    @Provides
    fun noteView(fragment: Fragment): AddTaskContracts.View{
        return fragment as AddTaskContracts.View
    }

    @Provides
    fun deleteAllTask(fragment: Fragment): DeleteAllContracts.View{
        return fragment as DeleteAllContracts.View
    }

}