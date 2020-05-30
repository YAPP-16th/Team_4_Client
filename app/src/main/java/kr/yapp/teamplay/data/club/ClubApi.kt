/*
 * Created by Lee Oh Hyoung on 2020/05/30 .. 
 */
package kr.yapp.teamplay.data.club

import io.reactivex.Single
import retrofit2.http.GET

interface ClubApi {

    @GET("/clubs/characters/infos")
    fun getTeamCharacters(): Single<TeamCharacterResponse>
}
