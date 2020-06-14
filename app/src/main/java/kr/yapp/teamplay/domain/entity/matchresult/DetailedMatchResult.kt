/*
 * Created by Lee Oh Hyoung on 2020/06/14 .. 
 */
package kr.yapp.teamplay.domain.entity.matchresult

data class DetailedMatchResult(
    val guestName: String,
    val hostName: String,
    val matchDetailResultScores: List<MatchResultScorePerType>
)
