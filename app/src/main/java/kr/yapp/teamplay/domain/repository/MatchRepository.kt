package kr.yapp.teamplay.domain.repository

import io.reactivex.Single
import kr.yapp.teamplay.data.match.MatchListResponse

interface MatchRepository {
    fun getMatchList(currentPage : Int): Single<MatchListResponse>
    fun getMatchSchedule()
    fun getMatchDetail()
}