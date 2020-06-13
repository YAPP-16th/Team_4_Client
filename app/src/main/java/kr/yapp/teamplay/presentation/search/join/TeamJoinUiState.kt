/*
 * Created by Lee Oh Hyoung on 2020/06/13 .. 
 */
package kr.yapp.teamplay.presentation.search.join

import kr.yapp.teamplay.domain.entity.TeamCharacter

sealed class TeamJoinUiState {

    abstract val message: String

    object JoinSuccess: TeamJoinUiState() {
        override val message: String
            get() = "동호회 가입에 성공하였습니다"
    }

    data class Content(
        override val message: String,
        val character: List<TeamCharacter>,
        val createDate: String,
        val location: String,
        val memberCount: Int,
        val teamName: String,
        val questions: List<String>
    ): TeamJoinUiState()

    data class Error(
        override val message: String,
        val throwable: Throwable
    ): TeamJoinUiState()

}
