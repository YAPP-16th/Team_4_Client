package kr.yapp.teamplay.data.match

import io.reactivex.Single
import kr.yapp.teamplay.data.RetrofitManager
import kr.yapp.teamplay.domain.repository.MatchRepository

class MatchRepositoryImpl(
    private val matchApi: MatchApi =
        RetrofitManager.create(MatchApi::class.java)
) : MatchRepository {
    override fun getMatchList(
        currentPage: Int?, startTimeFrom: String?
        , startTimeTo: String?, location: String?
        , matchStyle: String?
    ) : Single<MatchListResponse>{
        return matchApi.getMatchList(
            null,null, location, matchStyle, currentPage,null,null,startTimeFrom, startTimeTo
        )
    }

    override fun getMatchDetail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}