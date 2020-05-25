package kr.yapp.teamplay.data.match

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchService {
    @GET("/v1/matches")
    fun getMatchList(@Query("all") all : Boolean?,
                     @Query("descending") descending : Boolean?,
                     @Query("location") location : String?,
                     @Query("matchStyle") matchStyle : String?,
                     @Query("page") page : Int?,
                     @Query("rowsPerPage") rowsPerPage : Int?,
                     @Query("sortBy") sortBy : String?,
                     @Query("startTime") startTime : String?) : Single<MatchListResponse>
}