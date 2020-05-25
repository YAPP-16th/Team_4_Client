package kr.yapp.teamplay.presentation.match_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.match.MatchList
import kr.yapp.teamplay.domain.entity.MatchInfo
import kr.yapp.teamplay.data.match.MatchRepositoryImpl
import kr.yapp.teamplay.domain.usecase.GetMatchDetail
import kr.yapp.teamplay.domain.usecase.GetMatchList
import kr.yapp.teamplay.domain.usecase.GetMatchSchedule
import kr.yapp.teamplay.presentation.util.SingleLiveEvent

class MatchListViewModel(
    private val getMatchList : GetMatchList =
        GetMatchList(MatchRepositoryImpl()),
    private val getMatchSchedule : GetMatchSchedule =
        GetMatchSchedule(MatchRepositoryImpl()),
    private val getMatchDetail: GetMatchDetail =
        GetMatchDetail(MatchRepositoryImpl())
) : ViewModel(){
    val itemClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val registerClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val matchList : MutableLiveData<MutableList<MatchList>> = MutableLiveData()
    val matchInfo : MutableLiveData<MatchInfo> = MutableLiveData()
    val currentPage : MutableLiveData<Int> = MutableLiveData()
    val matchFilter : MutableLiveData<String> = MutableLiveData()

    init {
        currentPage.value = 1
    }

    fun clickMatchListItem(matchInfo: MatchInfo) {
        this.matchInfo.value = matchInfo
        itemClick.call()
    }

    fun clickRegisterButton() {
        registerClick.call()
    }

    fun getListData() {
        getMatchList.getMatchList(currentPage.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (currentPage.value!! <= it.totalPage){
                    matchList.value = it.matchList
                    matchFilter.value = it.filterTitle
                    currentPage.value = it.currentPage+2 // Response.currentPage is View.currentPage minus 1
                }
            }, {
                Log.d("MyTag", it.localizedMessage.toString())
            })
    }
}