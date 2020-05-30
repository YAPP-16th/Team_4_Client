package kr.yapp.teamplay.data.match

import io.reactivex.Single
import kr.yapp.teamplay.data.RetrofitManager
import kr.yapp.teamplay.domain.repository.MatchRepository

class MatchRepositoryImpl(
    private val matchService: MatchService =
        RetrofitManager.create(MatchService::class.java)
) : MatchRepository {
    override fun getMatchList(currentPage : Int) : Single<MatchListResponse>{
        return matchService.getMatchList(
            null,null,null,null,currentPage,null,null,null
        )
    }

    override fun getMatchSchedule() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMatchDetail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}