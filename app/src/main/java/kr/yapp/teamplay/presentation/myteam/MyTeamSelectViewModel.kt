/*
 * Created by Lee Oh Hyoung on 2020/04/30 .. 
 */
package kr.yapp.teamplay.presentation.myteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kr.yapp.teamplay.presentation.util.SingleLiveEvent

class MyTeamSelectViewModel : ViewModel() {

    private val _searchClick = SingleLiveEvent<Any>()
    val searchClick: LiveData<Any> get() = _searchClick

    fun teamSearchCardClick() = _searchClick.call()

}
