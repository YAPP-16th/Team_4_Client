package kr.yapp.teamplay.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.teammain.TeamRepositoryImpl
import kr.yapp.teamplay.domain.entity.ClubListInfo
import kr.yapp.teamplay.domain.repository.TeamRepository
import kr.yapp.teamplay.presentation.BaseViewModel

/**
 * Created by Lee Oh Hyoung on 2020/05/23.
 */
class TeamSearchViewModel(
    private val teamRepository: TeamRepository =
        TeamRepositoryImpl()
) : BaseViewModel() {

    private val _teams = MutableLiveData<Pair<List<ClubListInfo>, Int>>()
    val teams: LiveData<Pair<List<ClubListInfo>, Int>> get() = _teams

    fun getAllClub() {
        Single.zip(
            teamRepository.getAllClub(),
            teamRepository.getAllClubCount(),
            BiFunction { clubList: List<ClubListInfo>, count: Int ->
                Pair(clubList, count) })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list -> _teams.value = list },
                { throwable -> showError(throwable.message.toString()) })
            .addDisposable()

    }

}
