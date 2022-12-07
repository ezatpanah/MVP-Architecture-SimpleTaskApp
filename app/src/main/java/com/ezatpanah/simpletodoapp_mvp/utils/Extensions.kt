package com.ezatpanah.simpletodoapp_mvp.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

//RxJava
fun <T : Any> Single<T>.applyScheduler(scheduler: Scheduler): Single<T> = subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())
fun <T : Any> Single<T>.applyIoScheduler() = applyScheduler(Schedulers.io())