package com.ezatpanah.simpletodoapp_mvp.ui.dialog

import com.ezatpanah.simpletodoapp_mvp.repository.DbRepository
import com.ezatpanah.simpletodoapp_mvp.ui.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DeleteAllPresenter @Inject constructor(
    private val repository: DbRepository,
    private val view : DeleteAllContracts.View
) : DeleteAllContracts.Presenter , BasePresenterImpl(){
    override fun deleteAllTask() {
        disposable = repository.deleteAllTask()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.deleteMessage()
            }
    }
}