package kr.yapp.teamplay.data.match

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchApi {

    @GET("/v1/matches")
    fun getMatchList(
        @Query("all") all : Boolean?,
        @Query("descending") descending : Boolean?,
        @Query("location") location : String?,
        @Query("matchStyle") matchStyle : String?,
        @Query("page") page : Int?,
        @Query("rowsPerPage") rowsPerPage : Int?,
        @Query("sortBy") sortBy : String?,
        @Query("startTimeFrom") startTimeFrom : String?,
        @Query("startTimeTo") startTimeTo : String?
    ): Single<MatchListResponse>

    @GET("/v1/matches/{matchId}/result/detail")
    fun getDetailedMatchResult(
        @Path("matchId") matchId: Int
    ): Single<MatchDetailedResultResponse>

    @GET("/v1/matches/{matchId}/result/individual")
    fun getDetailedMatchIndividualResult(
        @Path("matchId") matchId: Int
    ): Single<List<MatchIndividualResultResponse>>

}
