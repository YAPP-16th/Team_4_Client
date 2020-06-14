package kr.yapp.teamplay.presentation.match_detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.TeamPlayApplication
import kr.yapp.teamplay.data.match.CreateMatchRequest
import kr.yapp.teamplay.data.match.MatchRepositoryImpl
import kr.yapp.teamplay.data.teammain.TeamRepositoryImpl
import kr.yapp.teamplay.domain.entity.matchlist.MatchInfo
import kr.yapp.teamplay.domain.usecase.PostMatchRequest
import kr.yapp.teamplay.domain.usecase.TeamMainUseCase
import kr.yapp.teamplay.presentation.util.SingleLiveEvent
import kr.yapp.teamplay.util.PreferenceManager

class MatchDetailViewModel(
    private val matchRequestUseCase : PostMatchRequest =
        PostMatchRequest(MatchRepositoryImpl())
) : ViewModel() {
    val matchInfo : MutableLiveData<MatchInfo> = MutableLiveData()
    val matchRequestSuccess : SingleLiveEvent<Void> = SingleLiveEvent()
    val matchRequestFail : SingleLiveEvent<Void> = SingleLiveEvent()

    fun postMatchRequest(contact : String) {
        val createMatchRequest = CreateMatchRequest(
            PreferenceManager.getSelectedTeamId(TeamPlayApplication.appContext).toLong(),
            PreferenceManager.getUserId(TeamPlayApplication.appContext).toLong(), contact
        )

        matchRequestUseCase.requestMatch(createMatchRequest, matchInfo.value!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Request: " , it.hashCode().toString())
                matchRequestSuccess.call()
            }, {
                Log.d("Request: " , it.message)
                // this is successCode for matchRequest
                if (it.message == "stream was reset: INTERNAL_ERROR ") {
                    matchRequestSuccess.call()
                } else if (it.message!!.contains("500")) {
                    matchRequestSuccess.call()
                } else if (it.message!!.contains("200")) {
                    matchRequestSuccess.call()
                } else if (it.message!!.contains("End of input")) {
                    matchRequestSuccess.call()
                } else {
                    matchRequestFail.call()
                }
            })
    }
}