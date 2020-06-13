/*
 * Created by Lee Oh Hyoung on 2020/05/30 .. 
 */
package kr.yapp.teamplay.data.club

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface ClubApi {

    @GET("/clubs/characters/infos")
    fun getTeamCharacters(): Single<TeamCharacterResponse>

    @GET("/clubs/join/{clubId}")
    fun getClubJoinInfo(
        @Path("clubId") clubId: Int
    ): Single<GetClubJoinInfoResponse>

    @POST("/clubs/join")
    fun requestClubJoin(
        @Header("accessToken") accessToken: String,
        @Body clubId: Int
    ): Completable
}
