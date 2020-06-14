package kr.yapp.teamplay.domain.usecase

import io.reactivex.Single
import kr.yapp.teamplay.data.match.CreateMatchRequest
import kr.yapp.teamplay.data.match.MatchRepositoryImpl
import kr.yapp.teamplay.domain.repository.MatchRepository

class PostMatchRequest(
    private val repository : MatchRepository = MatchRepositoryImpl()
) {
    fun requestMatch(createMatchRequest: CreateMatchRequest, matchId: Long) : Single<Any>
            = repository.requestMatch(createMatchRequest, matchId)
}