/*
 * Created by Lee Oh Hyoung on 2020/05/30 .. 
 */
package kr.yapp.teamplay.data.club

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ClubApi {

    @GET("/clubs/characters/infos")
    fun getTeamCharacters(): Single<TeamCharacterResponse>

    @GET("/clubs/join/{clubId}")
    fun getClubJoinInfo(
        @Path("clubId") clubId: Int
    ): Single<GetClubJoinInfoResponse>
}
