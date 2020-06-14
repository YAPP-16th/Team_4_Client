package kr.yapp.teamplay.domain.repository

import io.reactivex.Single
import kr.yapp.teamplay.data.match.MatchDetailedResultResponse
import kr.yapp.teamplay.data.match.MatchIndividualResultResponse
import kr.yapp.teamplay.data.match.MatchListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchRepository {

    fun getMatchList(
        currentPage: Int?,
        startTimeFrom: String?,
        startTimeTo: String?,
        location: String?,
        matchStyle: String?
    ): Single<MatchListResponse>

    @GET("/v1/matches/{matchId}/result/detail")
    fun getDetailedMatchResult(
        @Path("matchId") matchId: Int
    ): Single<MatchDetailedResultResponse>

    @GET("/v1/matches/{matchId}/result/individual")
    fun getDetailedMatchIndividualResult(
        @Path("matchId") matchId: Int
    ): Single<List<MatchIndividualResultResponse>>
}