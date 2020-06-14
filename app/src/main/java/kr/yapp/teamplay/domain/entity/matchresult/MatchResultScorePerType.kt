/*
 * Created by Lee Oh Hyoung on 2020/06/14 .. 
 */
package kr.yapp.teamplay.domain.entity.matchresult

data class MatchResultScorePerType(
    val guestScore: Int,
    val hostScore: Int,
    val matchResultType: String
)
