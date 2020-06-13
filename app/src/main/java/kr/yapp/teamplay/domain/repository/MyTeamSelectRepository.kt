package kr.yapp.teamplay.domain.repository

import io.reactivex.Single
import kr.yapp.teamplay.data.myteam.MyTeamResponse

interface MyTeamSelectRepository {
    fun getMyTeams(): Single<MyTeamResponse>
}
