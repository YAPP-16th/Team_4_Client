/*
 * Created by Lee Oh Hyoung on 2020/05/09 .. 
 */
package kr.yapp.teamplay.presentation.myteam.create

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.TeamPlayApplication
import kr.yapp.teamplay.data.teamcreate.CreateClubRequest
import kr.yapp.teamplay.data.teamcreate.TeamCreateRepositoryImpl
import kr.yapp.teamplay.domain.usecase.TeamCreateUseCase
import kr.yapp.teamplay.util.PreferenceManager

class TeamCreateViewModel(
    private val teamCreateUseCase: TeamCreateUseCase =
        TeamCreateUseCase(TeamCreateRepositoryImpl())
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val createClubRequest: CreateClubRequest = CreateClubRequest()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


    fun createTeam(onClick: () -> Unit = {}, onFinish: () -> Unit) {
        onClick()
        val accessToken = PreferenceManager.getTokenKey(TeamPlayApplication.appContext)
        teamCreateUseCase.createClub(accessToken, createClubRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("TTT", "ok")
                onFinish()
            }, {
                Log.e("TTT", "error! : " + it.message) // 왜 에러뜨지? 알턱이 있나 생성되긴 함... 리스폰스 안맞춰줘서 그런듯
                onFinish()
            })
            .addTo(compositeDisposable)
    }

}
