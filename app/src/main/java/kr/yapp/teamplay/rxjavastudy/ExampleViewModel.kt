package kr.yapp.teamplay.rxjavastudy

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.rxjavastudy.model.Result
import kr.yapp.teamplay.rxjavastudy.network.ServiceApi

class ExampleViewModel {
    var list : List<Result> = ArrayList()
    val initListCallback : SingleLiveEvent<Void> = SingleLiveEvent()

    @SuppressLint("CheckResult")
    fun mapData(){
        val source : Observable<List<Result>> = ServiceApi.retrofitService.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        source//.subscribeOn(Schedulers.io())
            //.subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list = it
                initListCallback.call()
            }, {
                t: Throwable? ->
                t?.localizedMessage
            })
    }
}
