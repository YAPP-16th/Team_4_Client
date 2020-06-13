package kr.yapp.teamplay.presentation.teammain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.teammain.TeamRepositoryImpl
import kr.yapp.teamplay.data.teammain.response.TeamMainItemResponse
import kr.yapp.teamplay.domain.usecase.TeamMainUseCase
import kr.yapp.teamplay.presentation.util.SingleLiveEvent

class TeamMainViewModel(
    private val teamMainUseCase: TeamMainUseCase =
        TeamMainUseCase(TeamRepositoryImpl())
) : ViewModel() {

    companion object {
        private const val TAG: String = "TeamMainViewModel"
    }

    private val compositeDisposable: CompositeDisposable =
        CompositeDisposable()

    private val _showShimmer = MutableLiveData<Boolean>().apply { value = true }
    val showShimmer: LiveData<Boolean> get() = _showShimmer

    private val _teamMainItem = MutableLiveData<TeamMainItemResponse>()
    val teamMainItem: LiveData<TeamMainItemResponse> get() = _teamMainItem


    private val _matchScheduleClick = SingleLiveEvent<Any>()
    val matchScheduleClick : LiveData<Any> get() = _matchScheduleClick


    private val _matchListClick = SingleLiveEvent<Any>()
    val matchListClick : LiveData<Any> get() = _matchListClick

    var isAdmin: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    private fun startShimmer() {
        _showShimmer.value = true
    }

    private fun stopShimmer() {
        _showShimmer.value = false
    }

    fun fetchTeamMainItem(position : Int, toast: (message: String?) -> Unit = {}): Disposable {
        startShimmer()
        return teamMainUseCase.setClubInfo(position.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item ->
                _teamMainItem.value = item
                stopShimmer()
            }, {
                Log.e(TAG, "error: ${it.message}")
                stopShimmer()
                toast(it.message)
            })
            .addTo(compositeDisposable)
    }

    fun startSchedule() {
        _matchScheduleClick.call()
    }

    fun startMatchList() {
        _matchListClick.call()
    }
}