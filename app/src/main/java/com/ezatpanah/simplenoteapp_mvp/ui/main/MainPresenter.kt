package com.ezatpanah.simplenoteapp_mvp.ui.main

import com.ezatpanah.simplenoteapp_mvp.db.NoteEntity
import com.ezatpanah.simplenoteapp_mvp.repository.DbRepository
import com.ezatpanah.simplenoteapp_mvp.ui.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repository: DbRepository, private val view: MainContracts.View) :
    MainContracts.Presenter, BasePresenterImpl() {

        override fun loadAllNotes() {
            disposable = repository.loadAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        view.showAllNotes(it)
                    } else {
                        view.showEmpty()
                    }
                }
        }

        override fun deleteNote(entity: NoteEntity) {
            disposable = repository.deleteNote(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.deleteMessage()
                }
        }

        override fun filterNote(priority: String) {
            disposable = repository.filterNote(priority)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        view.showAllNotes(it)
                    } else {
                        view.showEmpty()
                    }
                }
        }

        override fun searchNote(title: String) {
            disposable = repository.searchNote(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        view.showAllNotes(it)
                    } else {
                        view.showEmpty()
                    }
                }
        }
    }