package kr.yapp.teamplay.domain.repository

import io.reactivex.Single
import kr.yapp.teamplay.data.match.MatchListResponse
import kr.yapp.teamplay.domain.entity.matchresult.DetailedMatchResult
import kr.yapp.teamplay.domain.entity.matchresult.MatchIndividualScore

interface MatchRepository {

    fun getMatchList(
        currentPage: Int?,
        startTimeFrom: String?,
        startTimeTo: String?,
        location: String?,
        matchStyle: String?
    ): Single<MatchListResponse>

    fun getDetailedMatchResult(matchId: Int): Single<DetailedMatchResult>

    fun getDetailedMatchIndividualResult(matchId: Int): Single<List<MatchIndividualScore>>

}
