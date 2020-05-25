package kr.yapp.teamplay.domain.usecase

import kr.yapp.teamplay.data.match.MatchRepositoryImpl
import kr.yapp.teamplay.domain.repository.MatchRepository

class GetMatchList (
    private val repository : MatchRepository = MatchRepositoryImpl()
) {
    fun getMatchList(currentPage : Int) =
        repository.getMatchList(currentPage)
}