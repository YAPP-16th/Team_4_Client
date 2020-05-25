package kr.yapp.teamplay.domain.usecase

import kr.yapp.teamplay.data.match.MatchRepositoryImpl
import kr.yapp.teamplay.domain.repository.MatchRepository

class GetMatchDetail(
    private val repository : MatchRepository = MatchRepositoryImpl()
) {
    fun getMatchDetail() {
        repository.getMatchDetail()
    }
}