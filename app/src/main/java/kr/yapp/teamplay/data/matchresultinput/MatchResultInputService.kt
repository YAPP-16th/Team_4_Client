package kr.yapp.teamplay.data.matchresultinput

import io.reactivex.Single
import kr.yapp.teamplay.domain.entity.matchresultinput.MatchResultInfo
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface MatchResultInputService {
    @POST("/v1/matches/{matchId}/matchResult")
    fun matchResultInput(@Path("matchId") matchId: Int, @Body matchResultInfo: MatchResultInfo)
            : Single<Void>
}