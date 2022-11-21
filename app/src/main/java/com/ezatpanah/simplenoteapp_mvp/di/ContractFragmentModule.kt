package com.ezatpanah.simplenoteapp_mvp.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.ezatpanah.simplenoteapp_mvp.ui.add.NoteContracts
import com.ezatpanah.simplenoteapp_mvp.ui.main.MainContracts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ContractFragmentModule {

    @Provides
    fun noteView(fragment: Fragment): NoteContracts.View{
        return fragment as NoteContracts.View
    }


}