package kr.yapp.teamplay.domain.usecase

import kr.yapp.teamplay.data.match.MatchRepositoryImpl
import kr.yapp.teamplay.domain.repository.MatchRepository

class GetMatchSchedule (
    private val repository : MatchRepository = MatchRepositoryImpl()
) {
    fun getMatchSchedule() {
        repository.getMatchSchedule()
    }
}