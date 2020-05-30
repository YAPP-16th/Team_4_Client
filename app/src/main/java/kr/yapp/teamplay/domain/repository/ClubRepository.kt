/*
 * Created by Lee Oh Hyoung on 2020/05/30 .. 
 */
package kr.yapp.teamplay.domain.repository

import io.reactivex.Single
import kr.yapp.teamplay.domain.entity.TeamCharacter

interface ClubRepository {

    fun getTeamCharacters(): Single<List<TeamCharacter>>
}
