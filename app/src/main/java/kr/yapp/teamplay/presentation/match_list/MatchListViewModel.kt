package kr.yapp.teamplay.presentation.match_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.match.MatchList
import kr.yapp.teamplay.domain.entity.matchlist.MatchInfo
import kr.yapp.teamplay.data.match.MatchRepositoryImpl
import kr.yapp.teamplay.domain.entity.matchlist.Search
import kr.yapp.teamplay.domain.usecase.GetMatchList
import kr.yapp.teamplay.domain.usecase.GetMatchSearchList
import kr.yapp.teamplay.presentation.util.SingleLiveEvent

class MatchListViewModel(
    private val getMatchList: GetMatchList =
        GetMatchList(MatchRepositoryImpl()),
    private val getMatchSearchList: GetMatchSearchList =
        GetMatchSearchList(MatchRepositoryImpl())
) : ViewModel() {
    val itemClick: SingleLiveEvent<Void> = SingleLiveEvent()
    val registerClick: SingleLiveEvent<Void> = SingleLiveEvent()

    private val _matchList: MutableLiveData<MutableList<MatchList>> = MutableLiveData()
    val matchList: LiveData<MutableList<MatchList>> get() = _matchList

    private val _matchInfo: MutableLiveData<MatchInfo> = MutableLiveData()
    val matchInfo: LiveData<MatchInfo> get() = _matchInfo

    val currentPage: MutableLiveData<Int> = MutableLiveData()

    private val _matchFilter: MutableLiveData<String> = MutableLiveData()
    val matchFilter: LiveData<String> get() = _matchFilter

    private val _search: MutableLiveData<Search> = MutableLiveData()
    val search: LiveData<Search> get() = _search

    init {
        currentPage.value = 1
    }

    fun clickMatchListItem(matchInfo: MatchInfo) {
        _matchInfo.value = matchInfo
        itemClick.call()
    }

    fun clickRegisterButton() {
        registerClick.call()
    }

    fun setSearch(search: Search) {
        _search.value = search
    }

    fun getListData() {
        getMatchList.getMatchList(currentPage.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (currentPage.value!! <= it.totalPage) {
                    _matchList.value = it.matchList
                    _matchFilter.value = it.filterTitle
                    currentPage.value =
                        it.currentPage + 2 // Response.currentPage is View.currentPage minus 1
                }
            }, {
                Log.d("MatchList", it.localizedMessage.toString())
            })
    }

    fun getSearchListData() {
        Log.d("MatchSearch", search.value!!.startTime)
        getMatchSearchList.getMatchSearchList(
            search.value!!.startTime, search.value!!.endTime,
            search.value!!.location, search.value!!.matchRule
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _matchList.value = it.matchList
                _matchFilter.value = it.filterTitle
            }, {
                Log.d("MatchSearch", it.localizedMessage.toString())
            })
    }
}