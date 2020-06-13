package kr.yapp.teamplay.data.teamcreate

import io.reactivex.Single
import kr.yapp.teamplay.data.RetrofitManager
import kr.yapp.teamplay.domain.repository.TeamCreateRepository

class TeamCreateRepositoryImpl(
    private val service: TeamCreateService =
        RetrofitManager.getRetrofit().create(
            TeamCreateService::class.java
        )
) : TeamCreateRepository {

    override fun teamCreate(accessToken: String?, createClubRequest: CreateClubRequest) : Single<Void> =
        service.createTeam(accessToken!!, createClubRequest)

}
