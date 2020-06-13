package kr.yapp.teamplay.domain.usecase

import io.reactivex.Single
import kr.yapp.teamplay.data.myteam.MyTeamResponse
import kr.yapp.teamplay.data.myteam.MyTeamSelectRepositoryImpl
import kr.yapp.teamplay.domain.repository.MyTeamSelectRepository

class MyTeamSelectUseCase(
    private val repository: MyTeamSelectRepository
) {
    fun getMyTeams(): Single<MyTeamResponse> = repository.getMyTeams()

}
