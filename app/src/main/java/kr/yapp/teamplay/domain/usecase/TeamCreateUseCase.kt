package kr.yapp.teamplay.domain.usecase

import kr.yapp.teamplay.data.teamcreate.CreateClubRequest
import kr.yapp.teamplay.domain.repository.TeamCreateRepository

class TeamCreateUseCase(
    private val repository: TeamCreateRepository
) {
    fun createClub(accessToken: String?, createClubRequest: CreateClubRequest) =
        repository.teamCreate(accessToken, createClubRequest)

}
