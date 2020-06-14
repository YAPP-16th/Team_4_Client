package kr.yapp.teamplay.data.creatematch

import io.reactivex.Single
import kr.yapp.teamplay.data.RetrofitManager
import kr.yapp.teamplay.domain.entity.creatematch.RequestCreateMatch
import kr.yapp.teamplay.domain.repository.CreateMatchRepository

class CreateMatchRepositoryImpl(
    val service : CreateMatchService =
        RetrofitManager.create(CreateMatchService::class.java)
) : CreateMatchRepository {
    override fun createCreateMatch(request: RequestCreateMatch?): Single<Any> =
        service.createMatch(request)
}