package kr.yapp.teamplay.data.match

import io.reactivex.Single
import retrofit2.http.*

interface MatchService {
    @GET("/v1/matches")
    fun getMatchList(@Query("all") all : Boolean?,
                     @Query("descending") descending : Boolean?,
                     @Query("location") location : String?,
                     @Query("matchStyle") matchStyle : String?,
                     @Query("page") page : Int?,
                     @Query("rowsPerPage") rowsPerPage : Int?,
                     @Query("sortBy") sortBy : String?,
                     @Query("startTimeFrom") startTimeFrom : String?,
                     @Query("startTimeTo") startTimeTo : String?) : Single<MatchListResponse>

    @POST("/v1/matches/{matchId}/matchRequest")
    fun requestMatch(@Body createMatchRequest: CreateMatchRequest,
                     @Path("matchId") matchId: Long) : Single<Any>
}