package kr.yapp.teamplay.domain.repository

import io.reactivex.Single
import kr.yapp.teamplay.data.match.CreateMatchRequest
import kr.yapp.teamplay.data.match.MatchListResponse

interface MatchRepository {
    fun getMatchList(currentPage: Int?, startTimeFrom: String?, startTimeTo: String?, location: String?, matchStyle: String?): Single<MatchListResponse>
    fun getMatchDetail()
    fun requestMatch(createMatchRequest: CreateMatchRequest, matchId: Long) : Single<Any>
}