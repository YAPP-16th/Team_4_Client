package kr.yapp.teamplay.data.myteam

import io.reactivex.Single
import kr.yapp.teamplay.TeamPlayApplication
import kr.yapp.teamplay.data.RetrofitManager
import kr.yapp.teamplay.data.matchresultinput.MatchResultInputService
import kr.yapp.teamplay.domain.repository.MyTeamSelectRepository
import kr.yapp.teamplay.util.PreferenceManager

class MyTeamSelectRepositoryImpl(
    private val service: MyTeamSelectService =
        RetrofitManager.getRetrofit().create(
            MyTeamSelectService::class.java
        )
) : MyTeamSelectRepository {
    override fun getMyTeams() : Single<MyTeamResponse> =
        service.getMyTeams(PreferenceManager.getTokenKey(TeamPlayApplication.appContext))

}
