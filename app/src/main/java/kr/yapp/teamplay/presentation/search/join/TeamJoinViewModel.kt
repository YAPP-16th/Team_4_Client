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
                _uiState.value = TeamJoinUiState.Error(throwable)
            })
            .addDisposable()
    }
}
