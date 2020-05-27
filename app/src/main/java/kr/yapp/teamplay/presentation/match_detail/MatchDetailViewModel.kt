package kr.yapp.teamplay.presentation.match_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.yapp.teamplay.domain.entity.MatchInfo

class MatchDetailViewModel : ViewModel() {
    val matchInfo : MutableLiveData<MatchInfo> = MutableLiveData()
}