package com.ezatpanah.simpletodoapp_mvp.ui.add

import com.ezatpanah.simpletodoapp_mvp.db.TaskEntity
import com.ezatpanah.simpletodoapp_mvp.repository.DbRepository
import com.ezatpanah.simpletodoapp_mvp.ui.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AddTaskPresenter @Inject constructor(
    private val repository: DbRepository,
    private val view: AddTaskContracts.View
) : AddTaskContracts.Presenter, BasePresenterImpl() {
    override fun saveTask(entity: TaskEntity) {
        disposable = repository.saveTask(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.close()
            }
    }

    override fun detailsTask(id: Int) {
        disposable = repository.detailsTask(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.loadTaskData(it)
            }
    }

    override fun updateTask(entity: TaskEntity) {
        disposable = repository.updateTask(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.close()
            }
    }
}