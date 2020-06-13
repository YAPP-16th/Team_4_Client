/*
 * Created by Lee Oh Hyoung on 2020/04/30 .. 
 */
package kr.yapp.teamplay.presentation.myteam

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.myteam.MyTeamResponse
import kr.yapp.teamplay.data.myteam.MyTeamSelectRepositoryImpl
import kr.yapp.teamplay.domain.entity.MyTeam
import kr.yapp.teamplay.domain.entity.teammain.ClubInfo
import kr.yapp.teamplay.domain.usecase.MyTeamSelectUseCase
import kr.yapp.teamplay.presentation.teammain.TeamMainViewModel
import kr.yapp.teamplay.presentation.util.SingleLiveEvent

class MyTeamSelectViewModel(
    private val usecase: MyTeamSelectUseCase =
        MyTeamSelectUseCase(MyTeamSelectRepositoryImpl())
) : ViewModel() {

    private val _searchClick = SingleLiveEvent<Any>()
    val searchClick: LiveData<Any> get() = _searchClick

    fun teamSearchCardClick() = _searchClick.call()

    var clubInfoList: MutableLiveData<MyTeamResponse> = MutableLiveData()


    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getMyTeams() {
        usecase.getMyTeams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item ->
                clubInfoList.value = item
            }, {
                Log.e("TTT", "ERROR ! " + it.message)
            })
            .addTo(compositeDisposable)
    }


}
