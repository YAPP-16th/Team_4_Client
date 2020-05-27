package kr.yapp.teamplay.domain.repository

import io.reactivex.Single
import kr.yapp.teamplay.data.teammain.response.TeamMainItemResponse
import kr.yapp.teamplay.domain.entity.ClubListInfo

interface TeamRepository {

    fun getTeamMainItem(clubId: String): Single<TeamMainItemResponse>

    fun getAllClub(): Single<List<ClubListInfo>>

    fun getAllClubCount(): Single<Int>
}
