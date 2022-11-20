package com.ezatpanah.simplenoteapp_mvp.ui.base

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.Disposable

open class BasePresenterImpl : BasePresenter{
    @NonNull
    var disposable: Disposable? = null

    override fun onStop() {
        disposable?.let {
            it.dispose()

        }
    }

}