package kr.yapp.teamplay.rxjava_study

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.rxjava_study.model.Result
import kr.yapp.teamplay.rxjava_study.network.ServiceApi

class ExampleViewModel {
    var list : List<Result> = ArrayList()
    val initListCallback : SingleLiveEvent<Void> = SingleLiveEvent()

    @SuppressLint("CheckResult")
    fun mapData(){
        val source : Observable<List<Result>> = ServiceApi.retrofitService.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        source.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list = it
                initListCallback.call()
            }, {
                t: Throwable? ->
                t?.localizedMessage
            })
    }
}