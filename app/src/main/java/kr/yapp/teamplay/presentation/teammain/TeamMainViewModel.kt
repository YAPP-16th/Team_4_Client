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

    fun fetchTeamMainItem(toast: (message: String?) -> Unit = {}): Disposable {
        startShimmer()
        return teamMainUseCase.setClubInfo("1")
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
}