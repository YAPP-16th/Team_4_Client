/*
 * Created by Lee Oh Hyoung on 2020/06/13 .. 
 */
package kr.yapp.teamplay.presentation.search.join

import kr.yapp.teamplay.domain.entity.TeamCharacter

sealed class TeamJoinUiState {

    data class Content(
        val character: List<TeamCharacter>,
        val createDate: String,
        val location: String,
        val memberCount: Int,
        val teamName: String,
        val questions: List<String>,
        val message: String
    ): TeamJoinUiState()

    data class Error(
        val throwable: Throwable
    ): TeamJoinUiState()

}
