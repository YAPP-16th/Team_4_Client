package kr.yapp.teamplay.domain.usecase

import io.reactivex.Single
import kr.yapp.teamplay.data.teammain.response.TeamMainItemResponse
import kr.yapp.teamplay.domain.repository.TeamRepository

class TeamMainUseCase(
    private val repository: TeamRepository
) {
    fun setClubInfo(clubId: String): Single<TeamMainItemResponse> =
        repository.getTeamMainItem(clubId)
}