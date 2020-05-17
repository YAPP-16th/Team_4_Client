package kr.yapp.teamplay.presentation.match_list

import androidx.lifecycle.ViewModel
import kr.yapp.teamplay.presentation.util.SingleLiveEvent

class MatchListViewModel : ViewModel(){
    val itemClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val registerClick : SingleLiveEvent<Void> = SingleLiveEvent()

    fun clickMatchListItem() {
        itemClick.call()
    }

    fun clickRegisterButton() {
        registerClick.call()
    }
}