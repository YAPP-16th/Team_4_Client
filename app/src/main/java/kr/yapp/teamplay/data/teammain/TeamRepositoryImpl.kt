package kr.yapp.teamplay.data.teammain

import io.reactivex.Single
import kr.yapp.teamplay.data.RetrofitManager
import kr.yapp.teamplay.data.search.toEntity
import kr.yapp.teamplay.data.teammain.response.TeamMainItemResponse
import kr.yapp.teamplay.domain.entity.ClubListInfo
import kr.yapp.teamplay.domain.repository.TeamRepository

class TeamRepositoryImpl(
    private val teamService: TeamService =
        RetrofitManager.getRetrofit().create(TeamService::class.java)
) : TeamRepository {

    override fun getTeamMainItem(clubId: String): Single<TeamMainItemResponse> {
        return teamService.getTeamMainInfo(clubId)
    }

    override fun getAllClub(): Single<List<ClubListInfo>> {
        return teamService.getAllClub()
            .map { response -> response.list?.map { it.toEntity() } }
    }

    override fun getAllClubCount(): Single<Int> {
        return teamService.getAllClub()
            .map { response -> response.resultCount }
    }
}
