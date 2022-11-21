package com.ezatpanah.simplenoteapp_mvp.ui.add

import com.ezatpanah.simplenoteapp_mvp.db.NoteEntity
import com.ezatpanah.simplenoteapp_mvp.repository.DbRepository
import com.ezatpanah.simplenoteapp_mvp.ui.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NotePresenter @Inject constructor(
    private val repository :DbRepository,
    private val view : NoteContracts.View
) : NoteContracts.Presenter , BasePresenterImpl() {
    override fun saveNote(entity: NoteEntity) {
        disposable=repository.saveNote(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.close()
            }
    }

    override fun detailsNote(id: Int) {
        disposable=repository.detailsNote(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.loadNoteData(it)
            }
    }

    override fun updateNote(entity: NoteEntity) {
        disposable=repository.updateNote(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.close()
            }
    }
}