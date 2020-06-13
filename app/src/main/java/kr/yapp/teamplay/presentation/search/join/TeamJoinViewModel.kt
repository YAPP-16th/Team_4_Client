/*
 * Created by Lee Oh Hyoung on 2020/06/13 .. 
 */
package kr.yapp.teamplay.presentation.search.join

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.club.ClubRepositoryImpl
import kr.yapp.teamplay.domain.repository.ClubRepository
import kr.yapp.teamplay.presentation.BaseViewModel

class TeamJoinViewModel(
    private val clubRepository: ClubRepository =
        ClubRepositoryImpl()
) : BaseViewModel() {

    private val _uiState = MutableLiveData<TeamJoinUiState>()
    val uiState: LiveData<TeamJoinUiState> get() = _uiState

    fun getClubJoinInfo(clubId: Int) {
        clubRepository.getClubJoinInfo(clubId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ clubJoinInfo ->
                _uiState.value = TeamJoinUiState.Content(
                    character = clubJoinInfo.character ,
                    createDate = clubJoinInfo.createDate,
                    location = clubJoinInfo.location,
                    memberCount = clubJoinInfo.memberCount,
                    teamName = clubJoinInfo.teamName,
                    questions = clubJoinInfo.questions,
                    message = clubJoinInfo.message
                )
            }, { throwable ->
                Log.d("TeamJoinViewModel", throwable.message.toString())
                _uiState.value = TeamJoinUiState.Error(message = "동호회 정보를 불러오지 못하였습니다", throwable = throwable)
            })
            .addDisposable()
    }

    fun requestClubJoin(
        accessToken: String,
        clubId: Int
    ) {
        clubRepository.requestClubJoin(accessToken, clubId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _uiState.value = TeamJoinUiState.JoinSuccess
            }, { throwable ->
                _uiState.value = TeamJoinUiState.Error(message = "이미 가입된 동호회 입니다", throwable = throwable)
            })
            .addDisposable()
    }
}
