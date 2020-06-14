/*
 * Created by Lee Oh Hyoung on 2020/06/14 .. 
 */
package kr.yapp.teamplay.presentation.match_result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.match.MatchRepositoryImpl
import kr.yapp.teamplay.domain.entity.matchresult.DetailedMatchResult
import kr.yapp.teamplay.domain.entity.matchresult.MatchIndividualScore
import kr.yapp.teamplay.domain.repository.MatchRepository
import kr.yapp.teamplay.presentation.BaseViewModel

class MatchDetailedResultViewModel(
    private val matchRepository: MatchRepository =
        MatchRepositoryImpl()
) : BaseViewModel() {

    private val _uiState = MutableLiveData<MatchDetailedResultUiState>()
    val uiState: LiveData<MatchDetailedResultUiState> get() = _uiState

    fun getMatchDetailedResult(matchId: Int) {
        Single.zip(
            matchRepository.getDetailedMatchResult(matchId),
            matchRepository.getDetailedMatchIndividualResult(matchId),
            BiFunction { result: DetailedMatchResult, individual: List<MatchIndividualScore> ->
                MatchDetailedResultUiState.Content(
                    message = "경기 결과를 정상적으로 가져왔습니다",
                    guestName = result.guestName,
                    hostName = result.hostName,
                    resultScores = result.matchDetailResultScores,
                    individualScore = individual
                )
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ content ->
                _uiState.value = content
            }, { throwable ->
                _uiState.value = MatchDetailedResultUiState.Error(
                    message = "경기 결과를 가져오지 못하였습니다",
                    throwable = throwable
                )
            })
            .addDisposable()
    }

}
